package com.acko.ClaimChain.ClaimChain.clients;

import com.acko.ClaimChain.ClaimChain.dto.ClaimsSuperDto;
import com.acko.ClaimChain.ClaimChain.dto.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "claims-service", url = "http://localhost:5010")
public interface BlockChainClient {

        @PostMapping("/claims/add")
        ResponseDto<String> addClaim(@RequestBody ClaimsSuperDto request);

        @GetMapping("/claims/search")
        List<ClaimsSuperDto> searchClaimByPhone(@RequestParam("claimId") String claimId);

}
