package com.luanvv.microservices.bookstore.product.services;

import com.luanvv.bookstore.product.specs.model.GenericResponse;
import com.luanvv.bookstore.product.specs.model.Product;
import com.luanvv.bookstore.product.specs.model.ProductRequest;
import com.luanvv.bookstore.product.specs.model.ProductsList;
import java.util.Optional;

public interface BookService {
    Optional<Product> findById(String id);

    ProductsList findAll(int page, int size);

    GenericResponse create(ProductRequest bookReq);

    void delete(String id);
}
