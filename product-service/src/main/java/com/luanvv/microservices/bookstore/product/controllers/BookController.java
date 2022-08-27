package com.luanvv.microservices.bookstore.product.controllers;

import com.luanvv.bookstore.audit.client.api.AuditApi;
import com.luanvv.bookstore.audit.client.model.RequestMessageModel;
import com.luanvv.bookstore.product.specs.api.ProductsApi;
import com.luanvv.bookstore.product.specs.model.GenericResponse;
import com.luanvv.bookstore.product.specs.model.Product;
import com.luanvv.bookstore.product.specs.model.ProductRequest;
import com.luanvv.bookstore.product.specs.model.ProductsList;
import com.luanvv.microservices.bookstore.product.services.AsyncService;
import com.luanvv.microservices.bookstore.product.services.BookService;
import com.luanvv.microservices.bookstore.product.services.response.GenericResponseUtil;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BookController implements ProductsApi {

	private final AuditApi auditApi;

	private final AsyncService asyncService;

	private final BookService bookService;

	private final Environment env;


	@Override
	public ResponseEntity<ProductsList> getProducts(@Valid Integer page, @Valid Integer size) {
		log.info("Get all books");
		try {
			return ResponseEntity.ok(bookService.findAll(page, size));
		} finally {
		
			try {
				asyncService.run(() -> {
					auditApi.publish(getRequestMessageModel());
					return null;
				});
			} catch (Exception e) {
				log.error("Publish audit error", e);
			}
		}
	}

	private RequestMessageModel getRequestMessageModel() {
		return new RequestMessageModel()
				.id(UUID.randomUUID().toString())
				.eventAction("Query All books")
				.userId(getUserId())
				.createdDate(LocalDateTime.now())
				.serviceName(env.getProperty("spring.application.name"));
	}

	private String getUserId() {
		return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication()).map(
				Authentication::getName).orElse(null);
	}

	@Override
	public ResponseEntity<Product> getProduct(String bookId) {
		log.info("Get product [{}]", bookId);
		return ResponseEntity.of(bookService.findById(bookId));
	}

	@Override
	public ResponseEntity<GenericResponse> deleteProduct(@PathVariable String bookId) {
		try {
			bookService.delete(bookId);
			return ResponseEntity.ok(GenericResponseUtil.ok("Delete success", bookId));
		} catch (EntityNotFoundException e) {
			log.error("Cannot delete book because " + bookId + " not found", e);
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			log.error("Delete book " + bookId + " error", e);
			return ResponseEntity.internalServerError()
					.body(GenericResponseUtil.error("Delete error", bookId));
		}
	}

	@Override
	public ResponseEntity<GenericResponse> putProduct(String productId,
			@Valid ProductRequest productRequest) {
		productRequest.setId(productId);
		return postProduct(productRequest);
	}

	@Override
	public ResponseEntity<GenericResponse> postProduct(@Valid ProductRequest productRequest) {
		try {
			return ResponseEntity.ok(bookService.create(productRequest));
		} catch (Exception e) {
			log.error("Create book error", e);
			return ResponseEntity.internalServerError()
					.body(GenericResponseUtil.error("Create error"));
		}
	}
}
