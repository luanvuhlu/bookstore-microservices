package com.luanvv.microservices.bookstore.services;

import com.luanvv.microservices.bookstore.entities.Book;
import com.luanvv.microservices.bookstore.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository repository;

    @Override
    public Optional<Book> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Page<Book> findAll(int page, int size) {
        return repository.findAll(PageRequest.of(page, size));
    }
}
