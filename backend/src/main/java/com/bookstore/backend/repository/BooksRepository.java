package com.bookstore.backend.repository;

import com.bookstore.backend.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.*;

import java.util.List;

public interface BooksRepository extends JpaRepository<Books, Integer> {

    boolean existsByTitle(String title);

    @Modifying
    @Query(value = "SELECT * FROM Books WHERE deleted_at is null", nativeQuery = true)
    List<Books> findByDeleted_atIsNull();

}
