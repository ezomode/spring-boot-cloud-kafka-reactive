package com.ezomode.sbkafkastreams;

import com.ezomode.sbkafkastreams.kafka.KafkaStreams;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(KafkaStreams.class)
public class SbKafkaStreamsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbKafkaStreamsApplication.class, args);
    }
}
