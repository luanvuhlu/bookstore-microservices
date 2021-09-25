package com.luanvv.microservices.bookstore.services;

import com.luanvv.microservices.bookstore.entities.Book;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface BookService {
    Optional<Book> findById(String id);

    Page<Book> findAll(int page, int size);
}
