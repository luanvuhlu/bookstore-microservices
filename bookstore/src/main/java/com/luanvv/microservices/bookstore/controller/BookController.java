package com.luanvv.microservices.bookstore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

	@GetMapping("/books/")
	public String getBooks() {
		return "All books";
	}
}
