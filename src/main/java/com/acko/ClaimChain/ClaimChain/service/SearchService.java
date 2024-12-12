package com.acko.ClaimChain.ClaimChain.service;

import com.acko.ClaimChain.ClaimChain.dto.ResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SearchService {

    private final BlockChainService blockChainService;

    public ResponseDto<String> getClaimsSettlementRatioForInsurer(String insurerId) {
        return null ;// blockChainService.getClaimsSettlementRatioForInsurer(insurerId);
    }

}
