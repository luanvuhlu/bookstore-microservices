package com.luanvv.microservices.audit.event;

import java.util.function.Supplier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.luanvv.microservices.audit.model.RequestMessage;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.Many;

@Configuration
public class EventPublisherConfiguraion {

    @Bean
    public Many<RequestMessage> auditFlux() {
        return Sinks.many().multicast().onBackpressureBuffer();
    }

    @Bean
    public Supplier<Flux<RequestMessage>> auditSender() {
        return () -> auditFlux().asFlux();
    }
}
