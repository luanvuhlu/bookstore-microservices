package com.luanvv.microservices.bookstore.product.services;

import com.luanvv.bookstore.product.specs.model.GenericResponse;
import com.luanvv.bookstore.product.specs.model.MetaResponse;
import com.luanvv.bookstore.product.specs.model.Product;
import com.luanvv.bookstore.product.specs.model.ProductRequest;
import com.luanvv.bookstore.product.specs.model.ProductsList;
import com.luanvv.microservices.bookstore.product.entities.Book;
import com.luanvv.microservices.bookstore.product.repositories.BookRepository;
import com.luanvv.microservices.bookstore.product.services.response.GenericResponseUtil;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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
    public Optional<Product> findById(String id) {
        return repository.findById(id)
                .map(book -> modelMapper.map(
                    book,
                    Product.class
                ));
    }

    @Override
    public ProductsList findAll(int page, int size) {
        final var bookPage = repository.findAll(PageRequest.of(page, size));
        return new ProductsList()
            .data(
                bookPage.getContent()
                    .stream()
                    .map(book -> modelMapper.map(book, Product.class))
                    .collect(Collectors.toList())
            )
            .meta(new MetaResponse()
                .page(bookPage.getNumber())
                .totalElements(bookPage.getTotalElements())
                .size(bookPage.getSize())
                .numberOfElements(bookPage.getNumberOfElements())
                .totalPages(bookPage.getTotalPages())
            )
            ;
    }

    @Transactional
    @Override
    public GenericResponse create(ProductRequest bookReq) {
        Book book = modelMapper.map(bookReq, Book.class);
        repository.save(book);
        return GenericResponseUtil.ok("Create ok", modelMapper.map(book, Book.class));
    }

    @Transactional
    @Override
    public void delete(String id) {
        Book book = repository.getById(id);
        repository.delete(book);
    }
}
