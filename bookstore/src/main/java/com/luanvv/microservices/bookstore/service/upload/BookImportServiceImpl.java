package com.luanvv.microservices.bookstore.service.upload;

import java.io.InputStream;

import org.springframework.messaging.Message;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Sinks.Many;

@Service
@EnableAsync
@RequiredArgsConstructor
public class BookImportServiceImpl implements BookImportService {

	private final Many<Message<BookModel>> booksFlux;
	
	@Override
	@Async
	public void run(InputStream input) {
		
	}
}
