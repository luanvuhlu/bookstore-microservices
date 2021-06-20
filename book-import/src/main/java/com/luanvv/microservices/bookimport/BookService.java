package com.luanvv.microservices.bookimport;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookService {

	private final JdbcTemplate jdbcTemplate;

	@Transactional
	public void save(Book book) {
		jdbcTemplate.query("SELECT id FROM book WHERE title = ?", rs -> {
			if (rs.next()) {
				jdbcTemplate.execute("UPDATE book SET author=?, genre=?, height=?, publisher=? WHERE id=?", (PreparedStatementCallback<Void>) ps -> {
					ps.setString(1, book.getAuthor());
					ps.setString(2, book.getGenre());
					ps.setInt(3, book.getHeight());
					ps.setString(4, book.getPublisher());
					ps.setInt(5, rs.getInt(1));
					return null;
				});
			} else {
				jdbcTemplate.execute("INSERT INTO book (title, author, genre, height, publisher) VALUES(?, ?, ?, ?, ?)", (PreparedStatementCallback<Void>) ps -> {
					ps.setString(1, book.getTitle());
					ps.setString(2, book.getAuthor());
					ps.setString(3, book.getGenre());
					ps.setInt(4, book.getHeight());
					ps.setString(5, book.getPublisher());
					return null;
				});
			}

		}, book.getTitle());
	}

}
