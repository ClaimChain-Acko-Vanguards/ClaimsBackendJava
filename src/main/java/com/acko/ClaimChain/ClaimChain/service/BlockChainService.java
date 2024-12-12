package com.acko.ClaimChain.ClaimChain.service;

import com.acko.ClaimChain.ClaimChain.clients.BlockChainClient;
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

    private final BlockChainClient client;

    public String addDatatoBlockChain(ClaimsSuperDto dto){
        List<ClaimsSuperDto> pastClaims =client.searchClaimByPhone(dto.getPhoneNumber());
        //TODO: call fraud detection method and update fraud score
        Double fraudScore = 0d;
        dto.setFraudScore(fraudScore);
        client.addClaim(dto);
        return "Success";
    }

}
