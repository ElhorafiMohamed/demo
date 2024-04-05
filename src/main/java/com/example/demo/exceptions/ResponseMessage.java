package com.example.demo.exceptions;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Builder
public class ResponseMessage {
    private String message;
    public ResponseMessage(String message) {
        this.message=message;
    }
}
