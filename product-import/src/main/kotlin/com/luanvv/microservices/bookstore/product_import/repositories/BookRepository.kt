package com.luanvv.microservices.bookstore.product_import.repositories

import com.luanvv.microservices.bookstore.product_import.entities.Book
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository : JpaRepository<Book, String> {
    fun findByIsbn10(isbn10: String): Book?
}