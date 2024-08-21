package com.bookstore.backend.controller;

import com.bookstore.backend.entity.Books;
import com.bookstore.backend.requests.BooksRequests;
import com.bookstore.backend.service.BooksService;
import jakarta.validation.Valid;
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
    public Books createBook(@Valid @RequestBody BooksRequests newBookRequest) {
        return booksService.addBook(newBookRequest);
    }

    @GetMapping("/books/{id}")
    public Books getBook(@PathVariable Integer id) {
        return booksService.getBookById(id);
    }

    @GetMapping("/books/all")
    public Iterable<Books> getAllBooks() {
        return booksService.getAllBooks();
    }

    @PutMapping("/updateBook/{id}")
    public Optional<Books> updateBook(@Valid @PathVariable Integer id, @RequestBody BooksRequests updateBook) {
        return booksService.updateBookById(id, updateBook);
    }

    @DeleteMapping("/deleteBook/{id}")
    public void deleteBook(@Valid @PathVariable Integer id) {
        booksService.deleteBookById(id);
    }

    @DeleteMapping("/deleteBooks/all")
    public void deleteBooksAll() {
        booksService.deleteBooksAll();
    }
}
