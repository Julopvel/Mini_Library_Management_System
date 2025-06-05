package com.julopvel.mini_library_ms.book;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/*
* We are only to test the controller logic with mocked services
* therefore, we use @ExtendWith and @WebMvcTest
* */
@ExtendWith(SpringExtension.class)
@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private BookService bookService;

    private static final Logger LOGGER = LoggerFactory.getLogger(BookControllerTest.class);

    private List<BookDTO> createMockBooks() {
        return IntStream.range(0, 5)
                .mapToObj(i -> {
                    BookDTO mockBookDTO =  new BookDTO();
                    mockBookDTO.setId(i);
                    mockBookDTO.setTitle("Mock Book " + i);
                    mockBookDTO.setAuthor("Mock Author " + i);
                    mockBookDTO.setIsbn("00000" + i);
                    mockBookDTO.setAvailabilityStatus(true);
                    return mockBookDTO;
                })
                .toList();
    }

    @Test
    @DisplayName("Should return all books")
    void testGetAllBooks() throws Exception {

        // Creates 5 mocked BookDTO entries
        List<BookDTO> mockBooks = createMockBooks();

        // Mock the service call
        when(bookService.findAllBooks()).thenReturn(mockBooks);

        // Perform the GET request
        MvcResult result = mockMvc.perform(get("/books/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(5))
                .andExpect(jsonPath("$[0].title").value("Mock Book 0"))     //access the first element of the list
                .andExpect(jsonPath("$[1].title").value("Mock Book 1"))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        LOGGER.info("Response content: {}", content);

        // verifies that the method findAllBooks() was called in the service interaction
        verify(bookService).findAllBooks();
    }

    @Test
    @DisplayName("Should return paginated books")
    void getMany() throws Exception {
        Integer page = 1;
        Integer pageSize = 5;

        // Creates 5 mocked BookDTO entries
        List<BookDTO> mockBooks = createMockBooks();

        // Mock the service call
        when(bookService.findMany(page, pageSize)).thenReturn(mockBooks);

        // Perform the GET request
        mockMvc.perform(get("/books")
                .param("page", String.valueOf(page))
                .param("pageSize", String.valueOf(pageSize)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(5))
                .andExpect(jsonPath("$[0].title").value("Mock Book 0"))
                .andExpect(jsonPath("$[1].title").value("Mock Book 1"));

        // verifies that the method findMany() was called in the service interaction
        verify(bookService).findMany(1, 5);

    }

    @Test
    @DisplayName("Should create a new book")
    void testPost() throws Exception {

        BookDTO mockBookDTO = new BookDTO();
        mockBookDTO.setTitle("New Title");
        mockBookDTO.setAuthor("New Author");
        mockBookDTO.setIsbn("1234567890");
        mockBookDTO.setAvailabilityStatus(true);

        // Mock the service call
        when(bookService.create(mockBookDTO)).thenReturn(Optional.of(mockBookDTO));

        // Perform the POST request
        MvcResult result = mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                {
                    "title": "New Title",
                    "author": "New Author",
                    "isbn": "1234567890"
                }
            """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("New Title"))
                .andExpect(jsonPath("$.author").value("New Author"))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        LOGGER.info("Response content: {}", content);

        // verifies that the method create() was called in the service interaction
        verify(bookService).create(mockBookDTO);
    }

    @Test
    @DisplayName("Should update an existing book")
    void testPut() throws Exception {

        BookDTO mockBookDTO = new BookDTO(1, "Updated Title", "Updated Author", "1111111111", true);

        // Mock the service call
        when(bookService.update(any(BookDTO.class))).thenReturn(Optional.of(mockBookDTO));

        // Perform the PUT request
        mockMvc.perform(put("/books/1")     // id = 1
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                {
                    "title": "Updated Title",
                    "author": "Updated Author",
                    "isbn": "1111111111",
                    "availabilityStatus": true
                }
            """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Title"))
                .andExpect(jsonPath("$.author").value("Updated Author"));

        // verifies that the method update() was called in the service interaction
        verify(bookService).update(any(BookDTO.class));

    }

    @Test
    @DisplayName("Should delete a book by Id")
    void deleteById() throws Exception {
        Long bookId = 1L;

        // Mock the service call
        when(bookService.deleteById(bookId)).thenReturn(true);

        // Perform the DELETE request
        mockMvc.perform(delete("/books/{bookId}", bookId))
                .andExpect(status().isNoContent());

        // verifies that the method deleteById() was called in the service interaction
        verify(bookService).deleteById(1L);
    }
}