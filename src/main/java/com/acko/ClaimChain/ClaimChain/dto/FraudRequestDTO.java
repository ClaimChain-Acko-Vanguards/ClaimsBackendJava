package com.acko.ClaimChain.ClaimChain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class FraudRequestDTO {

    private List<Message> messages;
    @Data
    @Builder(toBuilder = true)
    @AllArgsConstructor
    // Inner Class for Message
    public static class Message {
        // Getters and Setters
        private String role;
        private String content;
    }

    @Override
    public String toString() {
        return "ChatRequestDTO{" +
                "messages=" + messages +
                '}';
    }
}

