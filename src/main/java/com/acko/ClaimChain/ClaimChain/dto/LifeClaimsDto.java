package com.acko.ClaimChain.ClaimChain.dto;

import lombok.Data;

@Data
public class LifeClaimsDto extends ClaimsSuperDto {
    private String nomineeName;       // Name of the nominee
    private double policyCoverage;    // Policy coverage in INR
}
