package com.acko.ClaimChain.ClaimChain.mapper;

import com.acko.ClaimChain.ClaimChain.dto.ClaimsSuperDto;
import com.acko.ClaimChain.ClaimChain.models.Claims;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ClaimsMapper {
    ClaimsMapper INSTANCE = Mappers.getMapper(ClaimsMapper.class);

    ClaimsSuperDto claimsToClaimsSuperDto(Claims claims);
    Claims claimsSuperDtoToClaims(ClaimsSuperDto claimsSuperDto);
    List<ClaimsSuperDto> claimsToClaimsSuperDto(List<Claims> claims);
    List<Claims> claimsSuperDtoToClaims(List<ClaimsSuperDto> claimsSuperDtos);

}