package com.brights.bookcrewproject3.security.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Data
@Table (name = "authors")
public class Author {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id ;

     @NotBlank
     @Column(name="first_name")
     private String firstName;

     @NotBlank
     @Column(name="last_name")
     private String lastName;

     @ManyToMany(mappedBy = "authors")
     private Set<Book> books;

}
