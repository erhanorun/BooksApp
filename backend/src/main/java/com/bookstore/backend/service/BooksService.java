package com.bookstore.backend.service;

import com.bookstore.backend.entity.Books;
import com.bookstore.backend.exceptions.ResourceAlreadyExistsException;
import com.bookstore.backend.exceptions.ResourceNotFoundException;
import com.bookstore.backend.repository.BooksRepository;
import com.bookstore.backend.requests.BooksRequests;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
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
        toSave.setPageCount(newBookRequest.getPageCount());
        toSave.setCreated_at(new Date());
        boolean bookTitle = booksRepository.existsByTitle(newBookRequest.getTitle());
        if (bookTitle) {
            throw new ResourceAlreadyExistsException("Book with the SAME TITLE already exists.");
        }
        return booksRepository.save(toSave);
    }

    public Iterable<Books> getAllBooks() {
        return booksRepository.findAll();
    }

    public Books getBookById(Integer id) {
        return booksRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No user by ID: " + id));
    }

    public Optional<Books> updateBookById(@Valid Integer id, BooksRequests updateBook) {
        Optional<Books> book = booksRepository.findById(id);
        if(book.isPresent()) {
            Books toUpdate = book.get();
            toUpdate.setTitle(updateBook.getTitle());
            toUpdate.setAuthor(updateBook.getAuthor());
            toUpdate.setPublisher(updateBook.getPublisher());
            toUpdate.setPageCount(updateBook.getPageCount());
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
