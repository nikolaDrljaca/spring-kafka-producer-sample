package com.example.springproducer.controller;

import com.example.springproducer.model.UserPayload;
import com.example.springproducer.model.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final KafkaTemplate<String, UserPayload> userPayloadKafkaTemplate;

    @PostMapping
    public void publishUser(@RequestBody UserRequest request) {
        //here we need to publish this as an event to kafka topic
        var userPayload = UserPayload.builder()
                .name(request.name)
                .email(request.email)
                .build();
        userPayloadKafkaTemplate.send("sample_mq", userPayload);
    }

}
