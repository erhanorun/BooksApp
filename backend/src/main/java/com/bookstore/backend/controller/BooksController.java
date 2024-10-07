package com.bookstore.backend.controller;

import com.bookstore.backend.entity.Books;
import com.bookstore.backend.requests.BooksRequests;
import com.bookstore.backend.service.BooksService;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1")
@Validated
@CrossOrigin(origins = "http://localhost:3000")
public class BooksController {

    private final BooksService booksService;

    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @PostMapping("/books")
    @Validated
    public Books createBook(@Valid @RequestBody BooksRequests newBookRequest) {
        return booksService.addBook(newBookRequest);
    }

    @GetMapping("/books/{id}")
    public Books getBook(@PathVariable Integer id) {
        return booksService.getBookById(id);
    }

    @GetMapping("/books")
    public Iterable<Books> getAllBooks() {
        return booksService.getAllBooks();
    }

    @PutMapping("/books/{id}")
    @Validated
    public Optional<Books> updateBook(@Valid @PathVariable Integer id, @RequestBody BooksRequests updateBook) {
        return booksService.updateBookById(id, updateBook);
    }

    @DeleteMapping("/deleteBook/{id}")
    public void deleteBook(@Valid @PathVariable Integer id) {
        booksService.deleteBookById(id);
    }

    @DeleteMapping("/deleteBooks")
    public void deleteBooksAll() {
        booksService.deleteBooksAll();
    }
}
