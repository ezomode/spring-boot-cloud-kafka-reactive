package com.ezomode.sbkafkastreams.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ezomode.sbkafkastreams.kafka.KafkaStreams;
import com.ezomode.sbkafkastreams.kafka.KafkaTopicListener;
import com.ezomode.sbkafkastreams.model.Wrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import reactor.core.publisher.Flux;

import java.io.IOException;

@Slf4j
@Service
public class KafkaService {

    private static ObjectMapper mapper = new ObjectMapper();

    private final KafkaStreams kafkaStream;
    private final KafkaTopicListener kafkaTopicListener;

    private Flux<Wrapper> flux;

    public KafkaService(KafkaStreams kafkaStreams, KafkaTopicListener kafkaTopicListener) {
        this.kafkaStream = kafkaStreams;
        this.kafkaTopicListener = kafkaTopicListener;

        this.flux = kafkaTopicListener.getFlux().map(this::fromJson);
    }

    private Wrapper fromJson(Object payload) {
        try {
            return mapper.readValue((byte[]) payload, Wrapper.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void push(Object payload) {

        Message message = MessageBuilder
                .withPayload(toJson(payload))
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON_VALUE)
                .build();

        kafkaStream.producerChannel().send(message);
    }

    private String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "could not jsonify...";
    }

    public Flux<?> getFlux() {
        return flux;
    }
}