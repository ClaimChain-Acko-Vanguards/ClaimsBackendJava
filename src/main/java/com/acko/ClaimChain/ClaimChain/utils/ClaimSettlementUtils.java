package com.acko.ClaimChain.ClaimChain.utils;

import com.acko.ClaimChain.ClaimChain.dto.ClaimsSuperDto;
import com.acko.ClaimChain.ClaimChain.models.Claims;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClaimSettlementUtils {

    public String getClaimRatio(List<Claims> claims) {
        if(CollectionUtils.isEmpty(claims))
            return "0";
        double totalClaimAmount = 0;
        double totalSettledAmount = 0;
        for (Claims claim : claims) {
            totalClaimAmount += claim.getClaimAmount();
            totalSettledAmount += claim.getSettlementAmount();
        }
        return String.valueOf(totalSettledAmount / totalClaimAmount);
    }

}
