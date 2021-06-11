package com.luanvv.microservices.bookimport;

import java.awt.print.Book;
import java.util.function.Function;

import org.springframework.context.annotation.Configuration;

@Configuration
public class BookProcessor {

	public Function<Book, Book> bookImport() {
		// TODO Insert database here
		return book -> book;
	}
}
