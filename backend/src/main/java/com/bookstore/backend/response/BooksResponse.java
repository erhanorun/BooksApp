package com.bookstore.backend.response;

import com.bookstore.backend.entity.Books;

public class BooksResponse {

    Integer id;
    String title;
    String author;
    String publisher;
    int pageCount;

    public BooksResponse(Books entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.publisher = entity.getPublisher();
        this.pageCount = entity.getPageCount();
    }
}
