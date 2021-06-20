package com.luanvv.microservices.bookstore.service.upload;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBeanBuilder;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Sinks.Many;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookImportServiceImpl implements BookImportService {

	private final Many<Message<BookModel>> booksFlux;

	@Override
	@Async
	public void run(InputStream input) throws IOException {
		try (var reader = new InputStreamReader(input)) {
			var columnStrategy = new ColumnPositionMappingStrategy<BookModel>();
			columnStrategy.setType(BookModel.class);
			var bookIterator = new CsvToBeanBuilder<BookModel>(reader)
					.withType(BookModel.class)
					.withMappingStrategy(columnStrategy)
					.withSkipLines(1)
					.build();
			
			for (var book : bookIterator) {
				log.debug("Process book {}", book);
				booksFlux.tryEmitNext(MessageBuilder.withPayload(book).build());
			}
		}
	}
}
