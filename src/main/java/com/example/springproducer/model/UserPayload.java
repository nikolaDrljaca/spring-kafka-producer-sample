package com.example.springproducer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserPayload {

    private final String tag = "user";
    public final String name;
    public final String email;

}
