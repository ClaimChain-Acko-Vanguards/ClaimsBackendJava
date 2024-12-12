package com.acko.ClaimChain.ClaimChain.dto;

import lombok.Data;

@Data
public class HealthClaimsDto extends ClaimsSuperDto{

    private String abhaId;                     // ABHA ID (for health claims)
    private String hospitalName;               // Name of the hospital
    private String diagnosisOrIllness;         // Diagnosis or illness description
    private String hospitalizationStartDate;   // Start date of hospitalization
    private String hospitalizationEndDate;     // End date of hospitalization
    private double totalMedicalExpenses;       // Total medical expenses in INR
    private double preApprovedAmount;          // Pre-approved amount in INR
    private String hospitalBills;              // Hospital bills
    private String testReports;                // Test reports
    private String initialAnalysis;            // Initial analysis of the case
    private String finalAnalysis;              // Final analysis of the case
    private boolean iotDataAvailable;          // IoT data availability
}
