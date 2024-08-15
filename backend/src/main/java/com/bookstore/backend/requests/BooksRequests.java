package com.bookstore.backend.requests;

import lombok.Data;

@Data
public class BooksRequests {

    Long id;
    String title;
    String author;
    String publisher;
    int page_count;

}
