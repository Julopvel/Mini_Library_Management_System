package com.julopvel.mini_library_ms.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookRepository bookRepository, BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO> booksList = bookService.findAllBooks();
        return ResponseEntity.ok(booksList);
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getMany(
            @RequestParam Integer page,
            @RequestParam Integer pageSize
    ) {
        List<BookDTO> books = bookService.findMany(page, pageSize);
        return ResponseEntity.ok(books);
    }

    @PostMapping
    public ResponseEntity<BookDTO> post(@RequestBody BookDTO bookDTO) {
        Optional<BookDTO> _book = bookService.create(bookDTO);
        if (_book.isEmpty()) {throw new RuntimeException("Could not create book" + bookDTO);}
        String uri = "/books/" + _book.get().getId();
        return ResponseEntity.created(URI.create(uri)).body(_book.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> put(@PathVariable Integer id, @RequestBody BookDTO bookDTO) {
        bookDTO.setId(id);      //since default value within DTO is 0, here we do a restart of the Id.
        Optional<BookDTO> _book = bookService.update(bookDTO);
        if (_book.isPresent()) {
            return ResponseEntity.ok(_book.get());
        } else {
            throw new RuntimeException("Could not update book: " + bookDTO);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookDTO> deleteById(@PathVariable Long id) {
        Boolean deleted = bookService.deleteById(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        throw new RuntimeException("Book not found for /books/" + id);
    }
}
