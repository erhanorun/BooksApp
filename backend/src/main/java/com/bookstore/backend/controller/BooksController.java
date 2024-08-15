package com.bookstore.backend.controller;

import com.bookstore.backend.entity.Books;
import com.bookstore.backend.requests.BooksRequests;
import com.bookstore.backend.service.BooksService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1")
public class BooksController {

    private final BooksService booksService;

    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @PostMapping("/addBook")
    public Books createBook(@RequestBody BooksRequests newBookRequest) {
        return booksService.addBook(newBookRequest);
    }

    @PutMapping("/updateBook/{id}")
    public Optional<Books> updateBook(@PathVariable Long bookId, @RequestBody BooksRequests updateBook) {
        return booksService.updateBookById(bookId, updateBook);
    }

    @GetMapping("/books/{id}")
    public Books getBook(@PathVariable Long bookId) {
        return booksService.getBookById(bookId);
    }

    @GetMapping("/books/all")
    public Iterable<Books> getAllBooks() {
        return booksService.getAllBooks();
    }

}
