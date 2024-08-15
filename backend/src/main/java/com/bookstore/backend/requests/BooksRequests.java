package com.bookstore.backend.requests;

import lombok.Data;

@Data
public class BooksRequests {

    Integer id;
    String title;
    String author;
    String publisher;
    int page_count;

}
