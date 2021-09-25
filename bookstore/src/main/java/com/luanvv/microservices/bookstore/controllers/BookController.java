package com.luanvv.microservices.bookstore.controllers;

import com.luanvv.microservices.bookstore.client.AuditClient;
import com.luanvv.microservices.bookstore.client.RequestMessage;
import com.luanvv.microservices.bookstore.entities.Book;
import com.luanvv.microservices.bookstore.services.AsyncService;
import com.luanvv.microservices.bookstore.services.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BookController {

	private final Tracer tracer;

	private final AuditClient auditClient;

	private final AsyncService asyncService;

	private final BookService bookService;

	@GetMapping("/books")
	public Page<Book> getBooks(int page, int size) {
		log.info("Get all books");
		try {
			return bookService.findAll(page, size);
		} finally {
			asyncService.run(() -> auditClient.publish(new RequestMessage("Query All books")));
		}
	}

	@GetMapping("/book/{bookId}")
	public Optional<Book> getBook(@PathVariable String bookId) throws InterruptedException {
		var millis = new SecureRandom().nextInt(1000);
		log.info(String.format("Sleeping for [%d] millis", millis));
		Thread.sleep(millis);
		Optional.ofNullable(this.tracer.currentSpan())
				.ifPresent(span -> span.tag("random-sleep-millis", String.valueOf(millis)));
		return bookService.findById(bookId);
	}
}
