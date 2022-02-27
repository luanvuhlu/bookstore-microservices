package com.luanvv.microservices.bookstore.product.controllers;

import com.luanvv.microservices.bookstore.product.client.AuditClient;
import com.luanvv.microservices.bookstore.product.client.RequestMessage;
import com.luanvv.microservices.bookstore.product.services.AsyncService;
import com.luanvv.microservices.bookstore.product.services.BookService;
import com.luanvv.microservices.bookstore.product.services.request.BookRequest;
import com.luanvv.microservices.bookstore.product.services.response.BookItemResponse;
import com.luanvv.microservices.bookstore.product.services.response.BookResponse;
import com.luanvv.microservices.bookstore.product.services.response.GenericResponse;
import java.security.SecureRandom;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BookController {

	private final Tracer tracer;

	private final AuditClient auditClient;

	private final AsyncService asyncService;

	private final BookService bookService;

	@GetMapping("/books")
	public Page<BookItemResponse> getBooks(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		log.info("Get all books");
		try {
			return bookService.findAll(page, size);
		} finally {
			asyncService.run(() -> auditClient.publish(new RequestMessage("Query All books")));
		}
	}

	@GetMapping("/book/{bookId}")
	public Optional<BookResponse> getBook(@PathVariable String bookId) throws InterruptedException {
		var millis = new SecureRandom().nextInt(1000);
		log.info(String.format("Sleeping for [%d] millis", millis));
		Thread.sleep(millis);
		Optional.ofNullable(this.tracer.currentSpan())
				.ifPresent(span -> span.tag("random-sleep-millis", String.valueOf(millis)));
		return bookService.findById(bookId);
	}

	@DeleteMapping("/book/{bookId}")
	public GenericResponse deleteBook(@PathVariable String bookId) {
		try {
			bookService.delete(bookId);
			return GenericResponse.ok("Delete success", bookId);
		} catch (EntityNotFoundException e) {
			log.error("Cannot delete book because " + bookId + " not found", e);
			return GenericResponse.error("Book not found", bookId);
		} catch (Exception e) {
			log.error("Delete book " + bookId + " error", e);
			return GenericResponse.error("Delete error", bookId);
		}
	}

	@PostMapping("/book")
	public GenericResponse createBook(@RequestBody BookRequest bookReq) {
		try {
			return bookService.create(bookReq);
		} catch (Exception e) {
			log.error("Create book error", e);
			return GenericResponse.error("Create error");
		}
	}
}
