package com.ezomode.sbkafkastreams.rest;

import com.ezomode.sbkafkastreams.model.Wrapper;
import com.ezomode.sbkafkastreams.service.KafkaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Date;

@RestController
public class StreamResource {

    private final KafkaService kafkaService;

    public StreamResource(KafkaService kafkaService) {
        this.kafkaService = kafkaService;
    }

    @PutMapping("/data")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void pushData(@RequestBody String payload) {
        kafkaService.push(new Wrapper(payload, new Date()));
    }

    @GetMapping(value = "/flux", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Flux<?> getFlux() {

        return kafkaService.getFlux()
                //                .take(Duration.ofSeconds(10))
                ;
    }

    @GetMapping(value = "/gen", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void generateInfinitely() {
        Flux.interval(Duration.ofMillis(200))
                .map(l -> new Wrapper("MSG " + l, new Date()))
                .subscribe(kafkaService::push);
    }
}