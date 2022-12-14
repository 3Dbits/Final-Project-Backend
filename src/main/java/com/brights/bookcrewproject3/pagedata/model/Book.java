package com.brights.bookcrewproject3.pagedata.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.*;

import java.util.Date;
import java.util.Set;


@Entity
@Table(name = "books")
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(min = 2, max = 250, message = "Title should be between 2 and 250 letters")
    private String title;

    @NotNull
    @Size(min = 2, max = 550, message = "Name of author should be between 2 and 550 characters")
    private String publisher;

    private String language;
    private boolean isAvailable; //PDF is available for eBook
    private String pdf; //link for pdf version of book

    private  String smallThumbnail;
    private  String thumbnail;

    private Date publishedDate;
    private String pageNumber;

    @NotNull
    @Size(min = 2, message = "Content should at least have 2 characters") //book content
    private  String content;

    @OneToMany(mappedBy = "book")
    private Set<Post> posts;

    @ManyToMany
    @JoinTable(
            name = "books_category",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    Set<Category> categories;

    @ManyToMany
    @JoinTable(
            name = "books_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    Set<Author> authors;

}




