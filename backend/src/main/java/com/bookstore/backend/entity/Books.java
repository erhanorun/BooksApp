package com.bookstore.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "books")
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name= "publisher")
    private String publisher;

    @Column(name = "page_count")
    private int page_count;

    @Column(name = "created_at")
    @CreationTimestamp
    private Date created_at;

    @Column(name = "updated_at")
    @CreationTimestamp
    private Date updated_at;

    @Column(name = "deleted_at")
    @CreationTimestamp
    private Date deleted_at;

    public Books(String title, String author, String publisher, int page_count, Date created_at, Date updated_at, Date deleted_at) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.page_count = page_count;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.deleted_at = deleted_at;
    }

}
