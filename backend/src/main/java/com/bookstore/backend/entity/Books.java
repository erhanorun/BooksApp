package com.bookstore.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

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
    @NotNull
    @NotEmpty
    @NotBlank
    private String title;

    @Column(name = "author")
    @NotNull
    @NotEmpty
    @NotBlank
    private String author;

    @Column(name= "publisher")
    @NotNull
    @NotEmpty
    @NotBlank
    private String publisher;

    @Column(name = "page_count")
    @NotNull
    @Positive
    @Range(min = 0, max = 10000)
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
