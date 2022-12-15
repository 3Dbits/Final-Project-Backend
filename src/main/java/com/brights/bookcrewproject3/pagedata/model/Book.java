package com.brights.bookcrewproject3.pagedata.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.*;

import java.util.Date;
import java.util.Set;


@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String title;

    @NotNull
    private String publisher;

    private String language;
    private boolean isAvailable; //PDF is available for eBook
    private String pdf; //link for pdf version of book

    private  String smallThumbnail;
    private  String thumbnail;

    private String publishedDate;
    private int pageNumber;

    @Lob
    private String content;

//    @OneToMany(mappedBy = "book")
//    private Set<Post> posts;

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

    public Book(String title,
                String publisher,
                String language,
                boolean isAvailable,
                String pdf,
                String smallThumbnail,
                String thumbnail,
                String publishedDate,
                int pageNumber,
                String content,
                Set<Category> categories,
                Set<Author> authors) {
        this.title = title;
        this.publisher = publisher;
        this.language = language;
        this.isAvailable = isAvailable;
        this.pdf = pdf;
        this.smallThumbnail = smallThumbnail;
        this.thumbnail = thumbnail;
        this.publishedDate = publishedDate;
        this.pageNumber = pageNumber;
        this.content = content;
        this.categories = categories;
        this.authors = authors;
    }
}





