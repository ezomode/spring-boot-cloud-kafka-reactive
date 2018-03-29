package com.ezomode.sbkafkastreams.kafka;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

import static com.ezomode.sbkafkastreams.Config.TOPIC_IN;
import static com.ezomode.sbkafkastreams.Config.TOPIC_OUT;

public interface KafkaStreams {

    @Input(TOPIC_IN)
    SubscribableChannel consumerChannel();

    @Output(TOPIC_OUT)
    MessageChannel producerChannel();
}