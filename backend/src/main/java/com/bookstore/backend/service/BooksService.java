package com.bookstore.backend.service;

import com.bookstore.backend.entity.Books;
import com.bookstore.backend.repository.BooksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BooksService {

    private final BooksRepository booksRepository;

    public Books save(Books books) {
        return booksRepository.save(books);

    }
}
