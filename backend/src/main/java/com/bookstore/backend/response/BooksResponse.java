package com.bookstore.backend.response;

import com.bookstore.backend.entity.Books;

public class BooksResponse {

    Long id;
    String title;
    String author;
    String publisher;
    int page_count;

    public BooksResponse(Books entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.publisher = entity.getPublisher();
        this.page_count = entity.getPage_count();
    }
}
