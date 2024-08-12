package com.bookstore.backend.controller;

import com.bookstore.backend.entity.Books;
import com.bookstore.backend.repository.BooksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class BooksController {

    private final BooksRepository booksRepository;

    @PostMapping("/books")
    public ResponseEntity<Books> createBooks(@RequestBody Books books) {
        try {
            Books _books = new Books();
            _books.setTitle(books.getTitle());
            _books.setAuthor(books.getAuthor());
            _books.setPublisher(books.getPublisher());
            _books.setPage_count(books.getPage_count());
            _books.setCreated_at(books.getCreated_at());

            Books savedBooks = booksRepository.save(_books);

            return new ResponseEntity<>(savedBooks, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
