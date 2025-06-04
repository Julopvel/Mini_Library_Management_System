package com.julopvel.mini_library_ms.book;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookService.class);
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    //TODO probar sin default values de page y pageSize
    public List<BookDTO> findMany(Integer page, Integer pageSize) {
        LOGGER.debug("findMany() called with page={}, pageSize={}", page, pageSize);
        return bookRepository.findMany(page, pageSize);
    }

    public List<BookDTO> findAllBooks() {
        LOGGER.debug("findAllBooks() called");
        return bookRepository.getListBooks();
    }

    public Optional<BookDTO> create(BookDTO bookDTO) {
        LOGGER.debug("create() called with book={}", bookDTO);

//        Boolean exists = bookRepository.existsByTitle(bookDTO.getTitle().toLowerCase());  //TODO probar con lower case
//        if (exists) {
//            throw new RuntimeException("Book already exists");
//        }
        Boolean saved = bookRepository.save(bookDTO.toBook());
        if (saved) {
            return Optional.of(bookDTO);
        }
        return Optional.empty();
    }

    public Optional<BookDTO> update(BookDTO bookDTO) {
        LOGGER.debug("update() called with book={}", bookDTO);
        Boolean updated = bookRepository.update(bookDTO.toBook());
        if (updated) {
            return Optional.of(bookDTO);
        }
        return Optional.empty();
    }

    public Boolean deleteById(Long id) {
        LOGGER.debug("deleteById() called with id={}", id);
        return bookRepository.deleteById(id);
    }

}
