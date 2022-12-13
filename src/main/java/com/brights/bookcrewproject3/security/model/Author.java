package com.brights.bookcrewproject3.security.model;

import lombok.Data;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Data
@Table (name = "authors")
public class Author {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id ;

     @NotNull
     @Column(name="first_name")
     private String first_name ;

     @NotNull
     @Column(name="last_name")
     private String last_name ;

     @ManyToMany(mappedBy = "authors")
     private Set<Book> books;

}
