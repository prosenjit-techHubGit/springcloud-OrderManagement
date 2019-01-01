package io.das.stream.kafka.config;

import org.springframework.cloud.stream.annotation.EnableBinding;

import io.das.stream.kafka.GreetingsStreams;

@EnableBinding(GreetingsStreams.class)
public class StreamsConfig {

}
