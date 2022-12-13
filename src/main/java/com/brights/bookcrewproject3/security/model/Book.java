package com.brights.bookcrewproject3.security.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.*;
import java.util.ArrayList;
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
    private String[] author;

    @NotNull
    @Size(min = 2, max = 550, message = "Name of author should be between 2 and 550 characters")
    private String publisher;

    @NotNull
    @Size(min = 2, max = 250, message = "Name of category should be between 2 and 250 characters")
    private  String categories;

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

}





