package com.acko.ClaimChain.ClaimChain.dto;

import com.acko.ClaimChain.ClaimChain.utils.EncryptionDecryptionUtils;
import lombok.Data;



@Data

public abstract class ClaimsSuperDto {
    private String ledgerId;               // Unique identifier for the ledger entry
    private String policyType;             // Type of insurance (Car, Bike, Health, etc.)
    private String policyNumber;           // Unique identifier for the insurance policy
    private String claimId;                // Unique identifier for the submitted claim
    private String claimantName;           // Name of the policyholder or claimant
    private String phoneNumber;            // Phone number of the claimant
    private String emailId;                // Email ID of the claimant
    private String aadharId;               // Aadhar ID of the claimant
    private String insurerName;            // Name of the insurer
    private String claimStatus;            // Current status of the claim
    private String claimDate;              // Date when the claim was filed
    private double claimAmount;            // Claim amount in INR
    private double settlementAmount;       // Settlement amount in INR
    private String settlementDate;         // Date of settlement
    private double fraudScore;             // Fraud score as a percentage
    private String pincode;                // Pincode of the claimant
    private String city;                   // City of the claimant
    private String state;                  // State of the claimant
    private String causeProof;             // Proof of cause for the claim
    private String causeStatement;         // Statement of cause for the claim
    private boolean thirdPartyInvolvement; // Indicates third-party involvement
    private String errorCodes;             // Error codes related to claim processing
    private String claimProcessingTime;    // Time taken to process the claim
    private String supportingDocuments;
    private String reasonForClaim;



    public String getClaimantName() {
        try {
            return EncryptionDecryptionUtils.decrypt(this.claimantName);
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting claimant name", e);
        }
    }

    public void setClaimantName(String claimantName) {
        try {
            this.claimantName = EncryptionDecryptionUtils.encrypt(claimantName);
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting claimant name", e);
        }
    }

    public String getPhoneNumber() {
        try {
            return EncryptionDecryptionUtils.decrypt(this.phoneNumber);
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting phone number", e);
        }
    }

    public void setPhoneNumber(String phoneNumber) {
        try {
            this.phoneNumber = EncryptionDecryptionUtils.encrypt(phoneNumber);
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting phone number", e);
        }
    }

    public String getEmailId() {
        try {
            return EncryptionDecryptionUtils.decrypt(this.emailId);
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting email ID", e);
        }
    }

    public void setEmailId(String emailId) {
        try {
            this.emailId = EncryptionDecryptionUtils.encrypt(emailId);
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting email ID", e);
        }
    }

    public String getAadharId() {
        try {
            return EncryptionDecryptionUtils.decrypt(this.aadharId);
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting Aadhar ID", e);
        }
    }




    public void setAadharId(String aadharId) {
        try {
            this.aadharId = EncryptionDecryptionUtils.encrypt(aadharId);
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting Aadhar ID", e);
        }
    }

// Supporting documents for the claim
}
