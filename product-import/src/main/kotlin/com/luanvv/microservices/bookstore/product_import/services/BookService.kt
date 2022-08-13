package com.luanvv.microservices.bookstore.product_import.services

import com.luanvv.microservices.bookstore.product_import.entities.Book
import com.luanvv.microservices.bookstore.product_import.repositories.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BookService(val repository: BookRepository) {

    @Transactional
    fun save(newBook: Book) {
        val existBook = repository.findByIsbn10(newBook.isbn10)
        val willSaveBook = existBook?.update(newBook) ?: newBook
        repository.save(willSaveBook)
    }

    private fun Book.update(book: Book): Book {
        title = book.title
        author = book.author
        genre = book.genre
        height = book.height
        publisher = book.publisher
        isbn10 = book.isbn10
        status = book.status
        return this
    }
}