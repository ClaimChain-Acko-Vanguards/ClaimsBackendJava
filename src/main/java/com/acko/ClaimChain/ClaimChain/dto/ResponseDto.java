package com.acko.ClaimChain.ClaimChain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDto<T> {
    private T data;
    private String message;
    private int errorCode;
}
