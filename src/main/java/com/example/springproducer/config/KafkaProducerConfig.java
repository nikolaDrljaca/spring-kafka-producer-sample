package com.example.springproducer.config;

import com.example.springproducer.model.MessagePayload;
import com.example.springproducer.model.UserPayload;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    /*
    Configuring our producer to use proper host:port, and proper [key]=value
    types for serialization.
     */
    public Map<String, Object> producerConfig() {
        var props = new HashMap<String, Object>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        //---
        props.put(JsonSerializer.TYPE_MAPPINGS, "MessagePayload:com.example.springproducer.model.MessagePayload, UserPayload:com.example.springproducer.model.UserPayload");
        return props;
    }

    @Bean
    public ProducerFactory<String, UserPayload> userPayloadProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    @Bean
    public KafkaTemplate<String, UserPayload> userPayloadKafkaTemplate(
            ProducerFactory<String, UserPayload> userPayloadProducerFactory
    ) {
        return new KafkaTemplate<>(userPayloadProducerFactory);
    }

    @Bean
    public ProducerFactory<String, MessagePayload> messagePayloadProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    @Bean
    public KafkaTemplate<String, MessagePayload> messagePayloadKafkaTemplate(
            ProducerFactory<String, MessagePayload> messagePayloadProducerFactory
    ) {
        return new KafkaTemplate<>(messagePayloadProducerFactory);
    }
}
