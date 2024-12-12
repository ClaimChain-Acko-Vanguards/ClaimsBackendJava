package com.acko.ClaimChain.ClaimChain.service;

import com.acko.ClaimChain.ClaimChain.dto.FraudRequestDTO;
import com.acko.ClaimChain.ClaimChain.dto.FraudScoreResponseDTO;
import com.acko.ClaimChain.ClaimChain.models.Claims;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FraudScoreService {

    private final ObjectMapper objectMapper;

    // Define the API endpoint
    String url = "https://acko-01-ai.openai.azure.com/openai/deployments/gpt-4o/chat/completions?api-version=2023-03-15-preview";

    // Define the API key
    String apiKey = ""; // Replace with your actual API key

    String requestBody = """
            {
                \"messages\": [
                    {
                        \"role\": \"system\",\"content\": \"You are a helpful assistant.\"
                    },
                    {
                        \"role\": \"user\",\"content\": \"pincode_fraud_percent: 100, number_of_claim_raised_in_15_days: 100, total_number_of_claim_raised: 500, total_number_of_claims_rejected: 10, policy_age: 1, claim_amount_by_coverage_amount_ratio: 4 , Please give a claim fraud score on a scale of 1-100 where 1 means less fraud and 100 means maximum fraud based on above data. Please dont write any explanation just give the fraud score as numeric value\"
                    }
                ]
            }
        """;


    public int getFraudScore(FraudRequestDTO requestDTO) {
        try {
            // Create the HttpClient instance
            HttpClient client = HttpClient.newHttpClient();
            // Build the HTTP POST request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .header("api-key", apiKey)
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            // Send the request and get the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            FraudScoreResponseDTO responseDTO = objectMapper.readValue(response.body(), FraudScoreResponseDTO.class);
            // Print the response
            System.out.println("Response Code: " + response.statusCode());
            System.out.println("Response Body: " + response.body());
            return Integer.parseInt(responseDTO.getChoices()[0].getMessage().getContent());
        } catch (Exception e) {
            // Handle any exceptions
            System.out.println("");
            return 0;
        }
    }

    public void calculateFraudScore(Claims claim , List<Claims> previousClaims, int last15daysClaim) {
        double claimAmount = claim.getClaimAmount() != 0 ? claim.getClaimAmount() : 0.0;
        double policyCoverage = claim.getPolicyCoverage() != 0 ? claim.getPolicyCoverage() : 1.0; // Avoid division by zero
        double fraudScore = claim.getFraudScore() != 0 ? claim.getFraudScore() : 0.0;
        String policyNumber = claim.getPolicyNumber() != null ? claim.getPolicyNumber() : "POLICY0000000000";
        int policyYear = 2024; // Default year in case parsing fails
        try {
            policyYear = Integer.parseInt(policyNumber.substring(policyNumber.length() - 4));
        } catch (Exception e) {
            // Keep default year if parsing fails
        }
        int policyAge = 2024 - policyYear;

        int claimsRaisedIn15Days = last15daysClaim;  // Example placeholder
        int totalClaimsRaised = previousClaims.size();   // Example placeholder
        int totalClaimsRejected = 14;  // Example placeholder
        double coverageAmountRatio = claim.getPolicyCoverage();

        String query = String.format("""
                pincode_fraud_percent: %.2f,
                number_of_claims_raised_in_15_days: %d,
                total_number_of_claims_raised: %d,
                total_number_of_claims_rejected: %d,
                policy_age: %d,
                claim_amount_by_coverage_amount_ratio: %.2f,

                Please give a claim fraud score on a scale of 1-100 where 1 means less fraud and 100 means maximum fraud based on the above data. Please don’t write any explanation, just give the fraud score as a numeric value.
                """,
                fraudScore, claimsRaisedIn15Days, totalClaimsRaised, totalClaimsRejected, policyAge, coverageAmountRatio);


        FraudRequestDTO fraudRequestDTO = FraudRequestDTO.builder().build();
        FraudRequestDTO.Message systemMessage = FraudRequestDTO.Message
                .builder()
                .role("system")
                .content("You are a helpful assistant.")
                .build();
        FraudRequestDTO.Message userMessage = FraudRequestDTO.Message
                .builder()
                .role("user")
                .content(query)
                .build();

        fraudRequestDTO.setMessages(Arrays.asList(systemMessage, userMessage));
        getFraudScore(fraudRequestDTO);
    }
}
