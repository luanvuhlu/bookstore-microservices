package com.luanvv.microservices.bookstore.services;

import com.luanvv.microservices.bookstore.services.request.BookRequest;
import com.luanvv.microservices.bookstore.services.response.BookItemResponse;
import com.luanvv.microservices.bookstore.services.response.BookResponse;
import com.luanvv.microservices.bookstore.services.response.GenericResponse;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface BookService {
    Optional<BookResponse> findById(String id);

    Page<BookItemResponse> findAll(int page, int size);

    GenericResponse create(BookRequest bookReq);

    void delete(String id);
}
