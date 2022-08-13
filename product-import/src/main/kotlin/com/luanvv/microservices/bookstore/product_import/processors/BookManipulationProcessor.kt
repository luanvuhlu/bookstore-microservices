package com.luanvv.microservices.bookstore.product_import.processors

import com.fasterxml.jackson.annotation.JsonProperty
import com.luanvv.microservices.bookstore.product_import.entities.Book
import com.luanvv.microservices.bookstore.product_import.services.BookService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.function.Consumer

@Configuration
class BookManipulationProcessor {

    @Bean
    fun processBookManipulation(
        bookService: BookService
    ): Consumer<BookEvent> = Consumer<BookEvent> { book ->
        bookService.save(book.toEntity())
    }
}

data class BookEvent(
    var id: Int = 0,
    var title: String = "",
    var author: String = "",
    var genre: String = "",
    var height: Int = 0,
    var publisher: String = "",
    var isbn_10: String = "",
    var status: String = "",
) {
    fun toEntity(): Book = Book(
        title = title,
        author = author,
        genre = genre,
        height = height,
        publisher = publisher,
        isbn10 = isbn_10,
        status = status,
    )
}