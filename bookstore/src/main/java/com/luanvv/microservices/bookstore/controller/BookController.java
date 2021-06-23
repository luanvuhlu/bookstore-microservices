package com.luanvv.microservices.bookstore.controller;

import java.io.IOException;
import java.util.Optional;
import java.util.Random;

import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.luanvv.microservices.bookstore.service.upload.BookImportService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BookController {

	private final BookImportService bookService;
	
	private final Tracer tracer;
	
	@GetMapping("/books")
	public String getBooks() {
		log.info("Get all books");
		return " All books";
	}
	
	@GetMapping("/book/{bookId}")
	public String getBook(@PathVariable int bookId) throws InterruptedException {
		var millis = new Random().nextInt(1000);
		log.info(String.format("Sleeping for [%d] millis", millis));
		Thread.sleep(millis);
		Optional.ofNullable(this.tracer.currentSpan())
			.ifPresent(span -> span.tag("random-sleep-millis", String.valueOf(millis)));
		return "Book: " + bookId;
	}

	@PostMapping("/book/import")
	public String importBooks(@RequestParam("file") MultipartFile file) throws IOException {
		bookService.run(file.getInputStream());
		return "Importing";
	}

}
