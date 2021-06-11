package com.luanvv.microservices.bookstore.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.luanvv.microservices.bookstore.service.upload.BookImportService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BookController {

	private final BookImportService bookService;
	
	@GetMapping("/books")
	public String getBooks() {
		return "All books";
	}

	@PostMapping("/book/import")
	public String importBooks(@RequestParam("file") MultipartFile file) throws IOException {
		bookService.run(file.getInputStream());
		return "Success";
	}

}
