package com.luanvv.microservices.bookimport;

import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class BookProcessor {

	private final BookService service;
	
    @Bean
	public Consumer<Book> booksImport() {
		return service::save;
	}
}
