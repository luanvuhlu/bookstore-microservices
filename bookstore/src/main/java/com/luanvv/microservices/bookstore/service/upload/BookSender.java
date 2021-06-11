package com.luanvv.microservices.bookstore.service.upload;

import java.util.function.Supplier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.Many;

@Configuration
public class BookSender {

	@Bean
	public Many<Message<BookModel>> booksFlux() {
		return Sinks.many().multicast().onBackpressureBuffer();
	}

	@Bean
	public Supplier<Flux<Message<BookModel>>> booksSender() {
		return () -> booksFlux().asFlux();
	}
}