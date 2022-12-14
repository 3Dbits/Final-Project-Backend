package com.brights.bookcrewproject3.pagedata.model;

import lombok.Data;

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
     private String firstName;

     @NotNull
     @Column(name="last_name")
     private String lastName;

     @ManyToMany(mappedBy = "authors")
     private Set<Book> books;

}
