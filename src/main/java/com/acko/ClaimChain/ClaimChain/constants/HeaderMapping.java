package com.acko.ClaimChain.ClaimChain.constants;

import java.util.HashMap;
import java.util.Map;

public class HeaderMapping {
    public static final Map<String, String> HEADER_TO_FIELD_MAP = new HashMap<>();

    static {
        // Common fields (SuperDTO)
        HEADER_TO_FIELD_MAP.put("Ledger ID", "ledgerId");
        HEADER_TO_FIELD_MAP.put("Policy Type", "policyType");
        HEADER_TO_FIELD_MAP.put("Policy Number", "policyNumber");
        HEADER_TO_FIELD_MAP.put("Claim ID", "claimId");
        HEADER_TO_FIELD_MAP.put("Claimant Name", "claimantName");
        HEADER_TO_FIELD_MAP.put("Phone Number", "phoneNumber");
        HEADER_TO_FIELD_MAP.put("Email ID", "emailId");
        HEADER_TO_FIELD_MAP.put("Aadhar ID", "aadharId");
        HEADER_TO_FIELD_MAP.put("Insurer Name", "insurerName");
        HEADER_TO_FIELD_MAP.put("Claim Status", "claimStatus");
        HEADER_TO_FIELD_MAP.put("Claim Date", "claimDate");
        HEADER_TO_FIELD_MAP.put("Claim Amount (₹)", "claimAmount");
        HEADER_TO_FIELD_MAP.put("Settlement Amount (₹)", "settlementAmount");
        HEADER_TO_FIELD_MAP.put("Settlement Date", "settlementDate");
        HEADER_TO_FIELD_MAP.put("Fraud Score (%)", "fraudScore");
        HEADER_TO_FIELD_MAP.put("Pincode", "pincode");
        HEADER_TO_FIELD_MAP.put("City", "city");
        HEADER_TO_FIELD_MAP.put("State", "state");
        HEADER_TO_FIELD_MAP.put("Cause Proof", "causeProof");
        HEADER_TO_FIELD_MAP.put("Cause Statement", "causeStatement");
        HEADER_TO_FIELD_MAP.put("Third-Party Involvement", "thirdPartyInvolvement");
        HEADER_TO_FIELD_MAP.put("Error Codes", "errorCodes");
        HEADER_TO_FIELD_MAP.put("Claim Processing Time", "claimProcessingTime");
        HEADER_TO_FIELD_MAP.put("Supporting Documents", "supportingDocuments");

        // Health-specific fields
        HEADER_TO_FIELD_MAP.put("Abha ID", "abhaId");
        HEADER_TO_FIELD_MAP.put("Hospital Name", "hospitalName");
        HEADER_TO_FIELD_MAP.put("Diagnosis/Illness", "diagnosisOrIllness");
        HEADER_TO_FIELD_MAP.put("Hospitalization Start Date", "hospitalizationStartDate");
        HEADER_TO_FIELD_MAP.put("Hospitalization End Date", "hospitalizationEndDate");
        HEADER_TO_FIELD_MAP.put("Total Medical Expenses (₹)", "totalMedicalExpenses");
        HEADER_TO_FIELD_MAP.put("Pre-Approved Amount (₹)", "preApprovedAmount");
        HEADER_TO_FIELD_MAP.put("Hospital Bills", "hospitalBills");
        HEADER_TO_FIELD_MAP.put("Test Reports", "testReports");
        HEADER_TO_FIELD_MAP.put("Initial Analysis", "initialAnalysis");
        HEADER_TO_FIELD_MAP.put("Final Analysis", "finalAnalysis");

        // Car/Bike-specific fields
        HEADER_TO_FIELD_MAP.put("Vehicle Registration No.", "vehicleRegistrationNo");
        HEADER_TO_FIELD_MAP.put("Vehicle Type", "vehicleType");
        HEADER_TO_FIELD_MAP.put("Vehicle Make and Model", "vehicleMakeAndModel");
        HEADER_TO_FIELD_MAP.put("Car/Bike Age", "carOrBikeAge");
        HEADER_TO_FIELD_MAP.put("Accident Date", "accidentDate");
        HEADER_TO_FIELD_MAP.put("Accident Location", "accidentLocation");
        HEADER_TO_FIELD_MAP.put("Garage Name", "garageName");
        HEADER_TO_FIELD_MAP.put("Repair Estimate (₹)", "repairEstimate");
        HEADER_TO_FIELD_MAP.put("Driving Behavior Data", "drivingBehaviorData");

        // Travel-specific fields
        HEADER_TO_FIELD_MAP.put("Travel Dates", "travelDates");
        HEADER_TO_FIELD_MAP.put("Trip Destination", "tripDestination");
        HEADER_TO_FIELD_MAP.put("Flight Details", "flightDetails");

        // Life-specific fields
        HEADER_TO_FIELD_MAP.put("Nominee Name", "nomineeName");
        HEADER_TO_FIELD_MAP.put("Policy Coverage (₹)", "policyCoverage");

        // Shared fields
        HEADER_TO_FIELD_MAP.put("Reason for Claim", "reasonForClaim");

        // IoT-specific fields
        HEADER_TO_FIELD_MAP.put("IoT Data Available", "iotDataAvailable");
    }
}