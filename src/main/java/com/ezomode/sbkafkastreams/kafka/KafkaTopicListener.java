package com.ezomode.sbkafkastreams.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import reactor.core.publisher.DirectProcessor;

import static com.ezomode.sbkafkastreams.Config.TOPIC_IN;

@Slf4j
@Component
public class KafkaTopicListener {

    private DirectProcessor flux = DirectProcessor.create();

    @StreamListener(TOPIC_IN)
    public void processInput(@Payload Object obj) {
        log.debug("{} received: {}", TOPIC_IN, obj);

        flux.onNext(obj);
    }

    public DirectProcessor<?> getFlux() {
        return flux;
    }

}