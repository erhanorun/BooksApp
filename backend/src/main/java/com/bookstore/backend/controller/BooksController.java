package com.bookstore.backend.controller;

import com.bookstore.backend.entity.Books;
import com.bookstore.backend.requests.BooksRequests;
import com.bookstore.backend.service.BooksService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Books createBook(@RequestBody @Valid BooksRequests newBookRequest) {
        return booksService.addBook(newBookRequest);
    }

    @GetMapping("/books/{id}")
    public Books getBook(@PathVariable Integer id) {
        return booksService.getBookById(id);
    }

    @GetMapping("/books")
    public Page<Books> getAllBooks(@RequestParam(defaultValue = "0") int pageNo,
                                   @RequestParam(defaultValue = "10") int pageSize) {
        return booksService.getAllBooks(pageNo, pageSize);
    }

    @GetMapping("/booksDeleted")
    public List<Books> getBooksDeleted_atIsNull() {
        return booksService.getBooksDeleted_atIsNull();
    }

    @PatchMapping("/books/{id}")
    @Validated
    public ResponseEntity<Books> updateBook(@PathVariable Integer id, @Valid @RequestBody Books book) {
        Books updatedBook = booksService.updateBook(id, book);
        return ResponseEntity.ok(updatedBook);
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
