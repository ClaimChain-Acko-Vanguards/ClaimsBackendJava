package com.acko.ClaimChain.ClaimChain.configuration;

import com.acko.ClaimChain.ClaimChain.mapper.ClaimsMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class MapperConfig {
    @Bean
    public ClaimsMapper claimsMapper() {
        return Mappers.getMapper(ClaimsMapper.class);
    }
}
