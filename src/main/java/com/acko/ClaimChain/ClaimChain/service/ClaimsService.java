package com.acko.ClaimChain.ClaimChain.service;

import com.acko.ClaimChain.ClaimChain.dto.ClaimsSuperDto;
import com.acko.ClaimChain.ClaimChain.dto.ResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@AllArgsConstructor
public class ClaimsService {

    private final ExcelReader excelReader;
    private final BlockChainService blockChainService;


    public ResponseDto<String> getClaimsSettlementRatioForInsurer(String insurerId) {
        return null ;// blockChainService.getClaimsSettlementRatioForInsurer(insurerId);
    }

    public ResponseDto<Object> readAndPushToBlockChain(MultipartFile file) throws  Exception{
        List<ClaimsSuperDto> superDtos =  excelReader.readExcel(file);
        if(CollectionUtils.isEmpty(superDtos)){
            return ResponseDto.builder().data("Empty data").build();
        }
        superDtos.forEach(blockChainService::addDatatoBlockChain);
        return null;
    }



}
