package com.acko.ClaimChain.ClaimChain.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ClaimsRequestDto {
    private String claimsCategory; //auto , health, life
    private String claimsInsurer;
    private String state;
    private Date startDate;
    private Date endDate;
    private String phoneNumber;
    private String pincode;
    private String email;
}
