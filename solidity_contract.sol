// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract ClaimsContract {
    struct Claim {
        string ledgerId;
        string policyType;
        string policyNumber;
        string claimId;
        string claimantName;
        string phoneNumber;
        string emailId;
        string aadharId;
        string insurerName;
        string claimStatus;
        string claimDate;
        uint256 claimAmount;
        uint256 settlementAmount;
        string settlementDate;
        uint256 fraudScore;
        string pincode;
        string city;
        string state;
        string causeProof;
        string causeStatement;
        bool thirdPartyInvolvement;
        string errorCodes;
        string claimProcessingTime;
        string supportingDocuments;
        string reasonForClaim;
    }

    mapping(string => Claim) private claimsByEmail;
    mapping(string => Claim) private claimsByPhone;
    mapping(string => Claim[]) private claimsByState;

    function addClaim(Claim memory claim) public {
        claimsByEmail[claim.emailId] = claim;
        claimsByPhone[claim.phoneNumber] = claim;
        claimsByState[claim.state].push(claim);
    }

    function getClaimByEmail(string memory emailId) public view returns (Claim memory) {
        return claimsByEmail[emailId];
    }

    function getClaimByPhone(string memory phoneNumber) public view returns (Claim memory) {
        return claimsByPhone[phoneNumber];
    }

    function getClaimsByState(string memory state) public view returns (Claim[] memory) {
        return claimsByState[state];
    }
}
