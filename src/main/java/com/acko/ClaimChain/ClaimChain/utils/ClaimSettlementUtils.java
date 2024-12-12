package com.acko.ClaimChain.ClaimChain.utils;

import com.acko.ClaimChain.ClaimChain.dto.ClaimsSuperDto;

import java.util.List;

public class ClaimSettlementUtils {

    private String getClaimRatio(List<ClaimsSuperDto> claimAmount) {
        double totalClaimAmount = 0;
        double totalSettledAmount = 0;
        for (ClaimsSuperDto claim : claimAmount) {
            totalClaimAmount += claim.getClaimAmount();
            totalSettledAmount += claim.getSettlementAmount();
        }
        return String.valueOf(totalSettledAmount / totalClaimAmount);
    }

}
