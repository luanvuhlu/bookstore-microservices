package com.luanvv.microservices.bookstore.controller;

import java.io.IOException;

<<<<<<< Updated upstream
=======
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.sleuth.Tracer;
>>>>>>> Stashed changes
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
	
<<<<<<< Updated upstream
	@GetMapping("/books")
	public String getBooks() {
		return "All books";
	}
=======
	private final Tracer tracer;
	
	@Value("${book.prefix:BookPrefix}")
	private String bookPrefix;
	
	
	@GetMapping("/books")
	public String getBooks() {
		log.info("Get all books");
		return bookPrefix + " All books";
	}
	
	@RequestMapping("/call")
	public Callable<String> call() {
		return new Callable<String>() {
			@Override
			public String call() throws Exception {
				var millis = new Random().nextInt(1000);
				Thread.sleep(millis);
				var currentSpan = BookController.this.tracer.currentSpan();
				if (currentSpan != null) {
					currentSpan.tag("callable-sleep-millis", String.valueOf(millis));
				}
				return "async hi: " + currentSpan;
			}
		};
	}
	
	@GetMapping("/book/{bookId}")
	public String getBook(@PathVariable int bookId) throws InterruptedException {
		var millis = new Random().nextInt(1000);
		log.info(String.format("Sleeping for [%d] millis", millis));
		Thread.sleep(millis);
		Optional.ofNullable(this.tracer.currentSpan())
			.ifPresent(span -> span.tag("random-sleep-millis", String.valueOf(millis)));
		return "Book: " + bookPrefix + " " + bookId;
	}
>>>>>>> Stashed changes

	@PostMapping("/book/import")
	public String importBooks(@RequestParam("file") MultipartFile file) throws IOException {
		bookService.run(file.getInputStream());
		return "Success";
	}

}
