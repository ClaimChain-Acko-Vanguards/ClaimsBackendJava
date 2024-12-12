package com.acko.ClaimChain.ClaimChain.controller;

import com.acko.ClaimChain.ClaimChain.dto.ClaimsRequestDto;
import com.acko.ClaimChain.ClaimChain.dto.ResponseDto;
import com.acko.ClaimChain.ClaimChain.models.Claims;
import com.acko.ClaimChain.ClaimChain.service.ClaimsService;
import com.acko.ClaimChain.ClaimChain.service.ExcelReader;
import lombok.AllArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@AllArgsConstructor
public class ClaimsController {


    private ClaimsService claimsService;

    @GetMapping("/getInsurer")
    public ResponseDto<Object> getTotalInsurer(){
        return claimsService.getInsurer();
    }

    @PostMapping("/getClaimsRatio")
    public ResponseDto<Object> getClaimRatio(@RequestBody ClaimsRequestDto claimsRequestDto){
        return claimsService.getClaimsRatioByCriteria(claimsRequestDto);
    }

    @PostMapping("/getClaims")
    public List<Claims> getClaims(@RequestBody ClaimsRequestDto claimsRequestDto){
        return claimsService.findClaimsByCriteria(claimsRequestDto);
    }



}
