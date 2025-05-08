package com.julopvel.mini_library_ms.book;

import com.julopvel.mini_library_ms.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepository {

    private final JdbcTemplate jdbcTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(BookRepository.class);

    @Autowired
    public BookRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static final String FIND_LIST_BOOKS = "SELECT id, title, author, isbn, availability_status FROM books";

    public ResponseEntity<List<Book>> getListBooks() {
        try {
            List<Book> listBooks = jdbcTemplate.query(FIND_LIST_BOOKS, ROW_MAPPER);
            //return (ResponseEntity<List<Book>>) jdbcTemplate.query(FIND_LIST_BOOKS, ROW_MAPPER).stream().toList();
            return new ResponseEntity<>(listBooks, HttpStatus.OK);
        } catch (DataAccessException e) {
            String error = "Error getting list of books";
            LOGGER.error(error, e);
            throw new RuntimeException(error, e);
        }
    }

    public static final String NEW_BOOK = "INSERT INTO books (title, author, isbn) VALUES (?, ?, ?)";

    public ResponseEntity<Book> newBook(Book book) {
        try {
//            int rowsAffected = jdbcTemplate.update(
//                    NEW_BOOK,
//                    book.author(),
//                    book.title(),
//                    book.isbn()
//                    //book.availabilityStatus()
            jdbcTemplate.update(
                    NEW_BOOK,
                    book.author(),
                    book.title(),
                    book.isbn()
            );
//            if (rowsAffected > 0) {
//                return new ResponseEntity<>(book, HttpStatus.CREATED);
//            }
//            else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (DataAccessException e) {
            String error = "Error creating new book";
            LOGGER.error(error, e);
            throw new RuntimeException(error, e);
        }
        return null;
    }




    private static final RowMapper<Book> ROW_MAPPER = (rs, rowNum) -> {
        Book book = new Book(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("author"),
                rs.getString("isbn"),
                rs.getBoolean("availability_status"));
                return book;
    };

}
