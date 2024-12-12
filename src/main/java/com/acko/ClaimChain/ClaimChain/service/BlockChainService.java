package com.acko.ClaimChain.ClaimChain.service;

import com.acko.ClaimChain.ClaimChain.dto.ClaimsSuperDto;
import com.acko.ClaimChain.ClaimChain.enums.SearchParameters;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BlockChainService {

    private final Web3j web3j;
    private final String contractAddress;
    private final Credentials credentials;
    private final ClaimsContract claimsContract;

    public BlockChainService(Web3j web3j, Credentials credentials, String contractAddress) {
        this.web3j = web3j;
        this.credentials = credentials;
        this.contractAddress = contractAddress;
        this.claimsContract = ClaimsContract.load(contractAddress, web3j, credentials, new DefaultGasProvider());
    }
    public String pushDataToBlockChain(ClaimsSuperDto claimDto){
        try {
            TransactionReceipt receipt = claimsContract.addClaim(
                    claimDto.getLedgerId(),
                    claimDto.getPolicyType(),
                    claimDto.getPolicyNumber(),
                    claimDto.getClaimId(),
                    claimDto.getClaimantName(),
                    claimDto.getPhoneNumber(),
                    claimDto.getEmailId(),
                    claimDto.getAadharId(),
                    claimDto.getInsurerName(),
                    claimDto.getClaimStatus(),
                    claimDto.getClaimDate(),
                    (long) claimDto.getClaimAmount(),
                    (long) claimDto.getSettlementAmount(),
                    claimDto.getSettlementDate(),
                    (long) claimDto.getFraudScore(),
                    claimDto.getPincode(),
                    claimDto.getCity(),
                    claimDto.getState(),
                    claimDto.getCauseProof(),
                    claimDto.getCauseStatement(),
                    claimDto.isThirdPartyInvolvement(),
                    claimDto.getErrorCodes(),
                    claimDto.getClaimProcessingTime(),
                    claimDto.getSupportingDocuments(),
                    claimDto.getReasonForClaim()
            ).send();
            return receipt.getTransactionHash();
        } catch (Exception e) {
            throw new RuntimeException("Error while adding claim to blockchain", e);
        }
    }

    public Claim getClaimByEmail(String emailId) {
        try {
            return claimsContract.getClaimByEmail(emailId).send();
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching claim by email", e);
        }
    }

    public Claim getClaimByPhone(String phoneNumber) {
        try {
            return claimsContract.getClaimByPhone(phoneNumber).send();
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching claim by phone number", e);
        }
    }

    public List<Claim> getClaimsByState(String state) {
        try {
            return claimsContract.getClaimsByState(state).send();
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching claims by state", e);
        }
    }

    public List<ClaimsSuperDto> searchDataFromBlockChain(String input, SearchParameters type) {
        List<ClaimsSuperDto> claimsSuperDtos = new ArrayList<>();
        switch (type) {
            case phone:
               claimsSuperDtos= searchByPhone(input);
                break;
            case vehicleRegistrationNumber:
               claimsSuperDtos= searchByVehicleRegistrationNumber(input);
                break;
            case aadharNumber:
                claimsSuperDtos =searchByAadharNumber(input);
                break;
            default:
                throw new IllegalArgumentException("Invalid search parameter type");
        }
        return claimsSuperDtos;
    }

    private List<ClaimsSuperDto> searchByPhone(String phone) {
        // Implement search logic by phone
    }

    private List<ClaimsSuperDto> searchByVehicleRegistrationNumber(String vehicleRegistrationNumber) {
        // Implement search logic by vehicle registration number
    }

    private List<ClaimsSuperDto> searchByAadharNumber(String aadharNumber) {
        // Implement search logic by aadhar number
    }
}
