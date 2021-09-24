package com.luanvv.microservices.bookimport;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {

    private final JdbcTemplate jdbcTemplate;

    @Transactional
    public int save(Book book) {
        List<Integer> ids = jdbcTemplate.query("SELECT id FROM book WHERE title = ? LIMIT 1", (rs, rnum) -> rs.getInt(1),
                book.getTitle());
        if (ids.isEmpty()) {
            return jdbcTemplate.update(
                    "INSERT INTO book (title, author, genre, height, publisher) VALUES(?, ?, ?, ?, ?)",
                    book.getTitle(), book.getAuthor(), book.getGenre(), book.getHeight(), book.getPublisher());
        } else {
            return jdbcTemplate.update("UPDATE book SET author=?, genre=?, height=?, publisher=? WHERE id=?",
                    book.getAuthor(), book.getGenre(), book.getHeight(), book.getPublisher(), ids.get(0));
        }

    }

}
