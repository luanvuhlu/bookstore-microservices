package com.luanvv.microservices.bookstore.product.repositories;

import com.luanvv.microservices.bookstore.product.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {

}
