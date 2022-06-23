package com.example.springproducer.controller;

import com.example.springproducer.model.MessagePayload;
import com.example.springproducer.model.MessageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/message")
@RequiredArgsConstructor
public class MessageController {

    private final KafkaTemplate<String, MessagePayload> messagePayloadKafkaTemplate;

    @PostMapping
    public void publishMessage(@RequestBody MessageRequest request) {
        //publish message to kafka topic
        //convert to payload
        var messagePayload = MessagePayload.builder()
                .content(request.content)
                .userId(request.userId)
                .build();

        messagePayloadKafkaTemplate.send("sample_mq", messagePayload);
    }

}
