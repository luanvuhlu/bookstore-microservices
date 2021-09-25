package com.luanvv.microservices.bookstore.controllers;

import java.security.SecureRandom;
import java.util.Optional;

import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.luanvv.microservices.bookstore.client.AuditClient;
import com.luanvv.microservices.bookstore.client.RequestMessage;
import com.luanvv.microservices.bookstore.services.AsyncService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BookController {

	private final Tracer tracer;

	private final AuditClient auditClient;
	
	private final AsyncService asyncService;

	@GetMapping("/books")
	public String getBooks() {
		log.info("Get all books");
		asyncService.run(() -> auditClient.publish(new RequestMessage("Query All books")));
		return " All books";
	}
	
	@GetMapping("/book/{bookId}")
	public String getBook(@PathVariable int bookId) throws InterruptedException {
		var millis = new SecureRandom().nextInt(1000);
		log.info(String.format("Sleeping for [%d] millis", millis));
		Thread.sleep(millis);
		Optional.ofNullable(this.tracer.currentSpan())
			.ifPresent(span -> span.tag("random-sleep-millis", String.valueOf(millis)));
		return "Book: " + bookId;
	}
}
