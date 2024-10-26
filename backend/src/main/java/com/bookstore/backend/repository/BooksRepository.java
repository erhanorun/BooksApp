package com.bookstore.backend.repository;

import com.bookstore.backend.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<Books, Integer> {

    boolean existsByTitle(String title);
    boolean existsByAuthor(String author);

}
