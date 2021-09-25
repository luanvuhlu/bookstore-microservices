package com.luanvv.microservices.bookstore.repositories;

import com.luanvv.microservices.bookstore.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {

}
