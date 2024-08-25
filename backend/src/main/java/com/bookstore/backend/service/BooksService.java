package com.bookstore.backend.service;

import com.bookstore.backend.entity.Books;
import com.bookstore.backend.repository.BooksRepository;
import com.bookstore.backend.requests.BooksRequests;
import com.bookstore.backend.response.BooksResponse;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Validated
public class BooksService {

    private final BooksRepository booksRepository;

    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public Books addBook(@Valid BooksRequests newBookRequest) {
        Books toSave = new Books();
        toSave.setId(newBookRequest.getId());
        toSave.setTitle(newBookRequest.getTitle());
        toSave.setAuthor(newBookRequest.getAuthor());
        toSave.setPublisher(newBookRequest.getPublisher());
        toSave.setPage_count(newBookRequest.getPage_count());
        toSave.setCreated_at(new Date());
        return booksRepository.save(toSave);
    }

    public Iterable<Books> getAllBooks() {
        return booksRepository.findAll();
    }

    public Books getBookById(Integer id) {
        return booksRepository.findById(id).orElse(null);
    }

    public Optional<Books> updateBookById(@Valid Integer id, BooksRequests updateBook) {
        Optional<Books> book = booksRepository.findById(id);
        if(book.isPresent()) {
            Books toUpdate = book.get();
            toUpdate.setTitle(updateBook.getTitle());
            toUpdate.setAuthor(updateBook.getAuthor());
            toUpdate.setPublisher(updateBook.getPublisher());
            toUpdate.setPage_count(updateBook.getPage_count());
            toUpdate.setUpdated_at(new Date());
            return Optional.of(booksRepository.save(toUpdate));
        } else {
            return Optional.empty();
        }
    }

    public Optional<Books> deleteBookById(Integer id) {
        Optional<Books> book = booksRepository.findById(id);
        if(book.isPresent()) {
            Books toDelete = book.get();
            toDelete.setDeleted_at(new Date());
            return Optional.of(booksRepository.save(toDelete));
        } else {
            return Optional.empty();
        }
    }

    public void deleteBooksAll() {
        booksRepository.deleteAll();
    }

}
