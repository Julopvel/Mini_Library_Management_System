package com.julopvel.mini_library_ms.book;

import com.julopvel.mini_library_ms.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    private BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks() {
        return bookRepository.getListBooks();
    }

    @PostMapping("/books")
    public ResponseEntity<Book> newBook(@RequestBody Book book) {
        return bookRepository.newBook(book);
    }
}
