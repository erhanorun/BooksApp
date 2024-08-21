package com.bookstore.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "books")
public class Books {

    @Id
    @SequenceGenerator(name="id_seq",
            sequenceName="id_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="id_seq")
    private Integer id;

    @Column(name = "title")
    @NotNull(message = "title must not be null")
    private String title;

    @Column(name = "author")
    @NotNull(message = "author must not be null")
    private String author;

    @Column(name= "publisher")
    @NotNull(message = "publisher must not be null")
    private String publisher;

    @Column(name = "page_count")
    @NotNull
    private int page_count;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated_at;

    @Column(name = "deleted_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deleted_at;
}
