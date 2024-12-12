package com.acko.ClaimChain.ClaimChain.service;

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

    public List<ClaimsSuperDto> searchDataFromBlockChain(String input, SearchParameters type) {
        List<ClaimsSuperDto> claimsSuperDtos = new ArrayList<>();
        switch (type) {
            case phone:
               claimsSuperDtos= searchByPhone(input);
                break;
            case vehicleRegistrationNumber:
               claimsSuperDtos= searchByVehicleRegistrationNumber(input);
                break;
            case aadharNumber:
                claimsSuperDtos =searchByAadharNumber(input);
                break;
            default:
                throw new IllegalArgumentException("Invalid search parameter type");
        }
        return claimsSuperDtos;
    }

    private List<ClaimsSuperDto> searchByPhone(String phone) {
        // Implement search logic by phone
    }

    private List<ClaimsSuperDto> searchByVehicleRegistrationNumber(String vehicleRegistrationNumber) {
        // Implement search logic by vehicle registration number
    }

    private List<ClaimsSuperDto> searchByAadharNumber(String aadharNumber) {
        // Implement search logic by aadhar number
    }
}
