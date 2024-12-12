package com.acko.ClaimChain.ClaimChain.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "CLAIMS")
@Data
public class Claims {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ledgerId;
    private String policyType;
    private String policyNumber;
    private String claimId;
    private String claimantName;
    private String phoneNumber;
    private String emailId;
    private String aadharId;
    private String insurerName;
    private String claimStatus;
    private String claimDate;
    private double claimAmount;
    private double settlementAmount;
    private Date settlementDate;
    private double fraudScore;
    private String pincode;
    private String city;
    private String state;
    private String causeProof;
    private String causeStatement;
    private boolean thirdPartyInvolvement;
    private String errorCodes;
    private String claimProcessingTime;
    private String supportingDocuments;
    private String reasonForClaim;

    // AutoClaimsDto fields
    private String vehicleRegistrationNo;
    private String vehicleType;
    private String vehicleMakeAndModel;
    private int carBikeAge;
    private String accidentDate;
    private String accidentLocation;
    private String garageName;
    private double repairEstimate;
    private String drivingBehaviorData;
    private boolean autoIotDataAvailable;

    // HealthClaimsDto fields
    private String abhaId;
    private String hospitalName;
    private String diagnosisOrIllness;
    private String hospitalizationStartDate;
    private String hospitalizationEndDate;
    private double totalMedicalExpenses;
    private double preApprovedAmount;
    private String hospitalBills;
    private String testReports;
    private String initialAnalysis;
    private String finalAnalysis;
    private boolean healthIotDataAvailable;

    // LifeClaimsDto fields
    private String nomineeName;
    private double policyCoverage;
}