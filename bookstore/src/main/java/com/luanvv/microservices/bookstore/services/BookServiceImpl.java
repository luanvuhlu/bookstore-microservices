package com.luanvv.microservices.bookstore.services;

import com.luanvv.microservices.bookstore.entities.Book;
import com.luanvv.microservices.bookstore.repositories.BookRepository;
import com.luanvv.microservices.bookstore.services.request.BookRequest;
import com.luanvv.microservices.bookstore.services.response.BookItemResponse;
import com.luanvv.microservices.bookstore.services.response.BookResponse;
import com.luanvv.microservices.bookstore.services.response.GenericResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
