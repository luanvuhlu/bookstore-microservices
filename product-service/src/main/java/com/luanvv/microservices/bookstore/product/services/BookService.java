package com.luanvv.microservices.bookstore.product.services;

import com.luanvv.microservices.bookstore.product.services.request.BookRequest;
import com.luanvv.microservices.bookstore.product.services.response.BookItemResponse;
import com.luanvv.microservices.bookstore.product.services.response.BookResponse;
import com.luanvv.microservices.bookstore.product.services.response.GenericResponse;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface BookService {
    Optional<BookResponse> findById(String id);

    Page<BookItemResponse> findAll(int page, int size);

    GenericResponse create(BookRequest bookReq);

    void delete(String id);
}
