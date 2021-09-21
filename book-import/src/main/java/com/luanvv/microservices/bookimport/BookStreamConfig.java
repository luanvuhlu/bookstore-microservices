package com.luanvv.microservices.bookimport;

import java.util.function.Consumer;
import java.util.function.Supplier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.Many;

@Configuration
@AllArgsConstructor
public class BookStreamConfig {

	private final BookService service;
	
    @Bean
	public Consumer<Book> booksImport() {
		return service::save;
	}
    
    @Bean
	public Many<Book> bookEmitter(){
		return Sinks.many().unicast().onBackpressureBuffer();
	}
	
    @Bean
	public Supplier<Flux<Book>> booksImportTestProducer() {
		return bookEmitter()::asFlux;
	}
}
