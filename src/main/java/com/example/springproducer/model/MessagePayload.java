package com.example.springproducer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MessagePayload {
    private final String tag = "msg";
    public final String content;
    public final Integer userId;
}
