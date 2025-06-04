package com.julopvel.mini_library_ms.book;

import com.julopvel.mini_library_ms.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BookDTO {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookDTO.class);

    private Integer id = 0;     // Default id
    private String title;
    private String author;
    private String isbn;
    private Boolean availabilityStatus = true;  // Default

    public BookDTO(){}

    public BookDTO(Integer id, String title, String author, String isbn, Boolean availabilityStatus) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.availabilityStatus = availabilityStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Boolean getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(Boolean availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }


    public Book toBook() {
        Book book = new Book();

        book.setId(id);
        book.setTitle(title);
        book.setAuthor(author);
        book.setIsbn(isbn);
        book.setAvailabilityStatus(availabilityStatus);
        LOGGER.debug("Book: {} mapped to BookData: {}", this, book);
        return book;
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", availabilityStatus=" + availabilityStatus +
                '}';
    }
}
