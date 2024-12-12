package com.acko.ClaimChain.ClaimChain.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class FraudScoreResponseDTO {
    private Choices[] choices;
    private long created;
    private String id;
    private String model;
    private String object;
    private String systemFingerprint;
    private Usage usage;

    // Getters and Setters

    @Data
    @RequiredArgsConstructor
    public static class Choices {
        private ContentFilterResult contentFilterResult;
        private String finishReason;
        private int index;
        private Object logprobs;
        private Message message;

        // Getters and Setters

        @Data
        @RequiredArgsConstructor
        public static class ContentFilterResult {
            private Error error;

            // Getters and Setters

            @Data
            @RequiredArgsConstructor
            static class Error {
                private String code;
                private String message;

                // Getters and Setters
            }
        }

        @Data
        @RequiredArgsConstructor
        public static class Message {
            private String content;
            private String role;

            // Getters and Setters
        }
    }

    @Data
    @RequiredArgsConstructor
    public static class Usage {
        private int completionTokens;
        private int promptTokens;
        private int totalTokens;

        // Getters and Setters
    }
}
