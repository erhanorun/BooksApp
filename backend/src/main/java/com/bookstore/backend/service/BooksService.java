package com.bookstore.backend.service;

import com.bookstore.backend.entity.Books;
import com.bookstore.backend.exceptions.ResourceNotFoundException;
import com.bookstore.backend.repository.BooksRepository;
import com.bookstore.backend.requests.BooksRequests;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

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
        toSave.setPageCount(newBookRequest.getPageCount());
        toSave.setCreated_at(new Date());
        boolean bookTitle = booksRepository.existsByTitle(newBookRequest.getTitle());
        if (bookTitle) {
            throw new ResourceNotFoundException("Book with the SAME TITLE already exists.");
        }
        return booksRepository.save(toSave);
    }

    public Page<Books> getAllBooks(int pageNo, int pageSize) {

        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);

        return booksRepository.findAll(pageRequest);
    }

    public Books getBookById(Integer id) {
        return booksRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No user by ID: " + id));
    }

    public List<Books> getBooksDeleted_atIsNull() {
         return booksRepository.findByDeleted_atIsNull();
    }

    public Books updateBook(@Valid Integer id, Books book) {
        Books existingBook = booksRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));

        if (book.getAuthor() == null || book.getAuthor().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Author is mandatory");
        }

        if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Title is mandatory");
        }

        if (book.getPublisher() == null || book.getPublisher().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Publisher is mandatory");
        }

        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setPublisher(book.getPublisher());
        existingBook.setPageCount(book.getPageCount());

        return booksRepository.save(existingBook);
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
