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

    private static final RowMapper<BookDTO> ROW_MAPPER = (rs, rowNum) -> {
        BookDTO bookDTO = new BookDTO(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("author"),
                rs.getString("isbn"),
                rs.getBoolean("availability_status"));
        return bookDTO;
    };

//TODO this will be pageable in the future
    private static final String FIND_LIST_BOOKS =
            "SELECT id, title, author, isbn, availability_status FROM books";

    public List<BookDTO> getListBooks() {
        try {
            List<BookDTO> listBooks = jdbcTemplate.query(FIND_LIST_BOOKS, ROW_MAPPER);
            return listBooks;
        } catch (DataAccessException e) {
            String error = "Error getting list of books";
            LOGGER.error(error, e);
            throw new RuntimeException(error, e);
        }
    }

    private static final String FIND_MANY =
            "SELECT id, title, author, isbn, availability_status FROM books ORDER BY id DESC LIMIT ?";

    public List<BookDTO> findMany(int page, int pageSize) {
        LOGGER.debug("findMany() called with page={}, pageSize={}", page, pageSize);
        try {
            int offset = (page - 1) * pageSize;
            return jdbcTemplate.query(FIND_MANY, ROW_MAPPER, pageSize, offset);
        } catch (DataAccessException e) {
            String error = "Cannot find users";
            LOGGER.error(error, e);
            throw new RuntimeException(error, e);
        }

    }

    private static final String NEW_BOOK =
            "INSERT INTO books (title, author, isbn) VALUES (?, ?, ?)";

/*TODO
*  display values in insomnia, including id and availabilityStatus*/
    public Boolean save(Book book) {
        try {
           int rowsAffected = jdbcTemplate.update(
                    NEW_BOOK,
                    book.getAuthor(),
                    book.getTitle(),
                    book.getIsbn()
            );
           return rowsAffected > 0;
        } catch (DataAccessException e) {
            String error = "Error creating new book";
            LOGGER.error(error, e);
            throw new RuntimeException(error, e);
        }
    }
/*TODO
*  handle null values, display the json within insomnia*/
    private static final String UPDATE =
            "UPDATE books SET title = ?, author = ?, isbn = ?, availability_status = ? WHERE id = ?";

    public Boolean update(Book book) {
        try {
            int rowsAffected = jdbcTemplate.update(
                    UPDATE,
                    book.getTitle(),
                    book.getAuthor(),
                    book.getIsbn(),
                    book.getAvailabilityStatus(),
                    book.getId()
            );
            return rowsAffected > 0;
        } catch (DataAccessException e) {
            String error = "Error updating book with id: " + book.getId();
            LOGGER.error(error, e);
            throw new RuntimeException(error, e);
        }
    }

    private static final String DELETE_BY_ID = "DELETE FROM books WHERE id = ?";

    public Boolean deleteById(Long id) {
        LOGGER.info("Deleting book with id: {}", id);
        try {
            int rowsAffected = jdbcTemplate.update(DELETE_BY_ID, id);
            return rowsAffected > 0;
        } catch (DataAccessException e) {
            String error = "Error deleting book with id: " + id;
            LOGGER.error(error, e);
            throw new RuntimeException(error, e);
        }
    }

    private static final String COUNT_BY_TITLE =
            "SELECT COUNT(*) FROM books WHERE title = ?";

    public Boolean existsByTitle(String title) {
        LOGGER.debug("existsByTitle() called with: title={}", title);
        try {
            int rowsAffected = jdbcTemplate.queryForObject(COUNT_BY_TITLE, Integer.class);
            LOGGER.debug("rowsAffected: {}",rowsAffected);
            return rowsAffected > 0;
        } catch (DataAccessException e) {
            String error = "Error checking if title exists";
            LOGGER.error(error, e);
            throw new RuntimeException(error, e);
        }
    }

}
