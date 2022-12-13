package com.brights.bookcrewproject3.security.model;

import lombok.Data;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@Table (name = "authors")
public class Author {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long author_id ;

     @NotBlank
     @Column(name="first_name")
     private String first_name ;

     @NotBlank
     @Column(name="last_name")
     private String last_name ;

     @ManyToOne
     @JoinColumn(name = "book_id", referencedColumnName="id")
     private Book book;

}
