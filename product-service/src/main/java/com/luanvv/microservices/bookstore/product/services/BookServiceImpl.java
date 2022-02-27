package com.luanvv.microservices.bookstore.product.services;

import com.luanvv.microservices.bookstore.product.entities.Book;
import com.luanvv.microservices.bookstore.product.repositories.BookRepository;
import com.luanvv.microservices.bookstore.product.services.request.BookRequest;
import com.luanvv.microservices.bookstore.product.services.response.BookItemResponse;
import com.luanvv.microservices.bookstore.product.services.response.BookResponse;
import com.luanvv.microservices.bookstore.product.services.response.GenericResponse;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository repository;

    private final ModelMapper modelMapper;

    @Override
    public Optional<BookResponse> findById(String id) {
        return repository.findById(id)
                .map(book -> modelMapper.map(book, BookResponse.class));
    }

    @Override
    public Page<BookItemResponse> findAll(int page, int size) {
        return repository.findAll(PageRequest.of(page, size))
                .map(book -> modelMapper.map(book, BookItemResponse.class));
    }

    @Transactional
    @Override
    public GenericResponse create(BookRequest bookReq) {
        Book book = modelMapper.map(bookReq, Book.class);
        repository.save(book);
        return GenericResponse.ok("Create ok", modelMapper.map(book, BookResponse.class));
    }

    @Transactional
    @Override
    public void delete(String id) {
        Book book = repository.getById(id);
        repository.delete(book);
    }
}
