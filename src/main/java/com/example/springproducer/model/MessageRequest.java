package com.example.springproducer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class MessageRequest {

    public final String content;
    public final Integer userId;

}
