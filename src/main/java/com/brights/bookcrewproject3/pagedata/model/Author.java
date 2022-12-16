package com.brights.bookcrewproject3.pagedata.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Data
@Table (name = "authors")
@NoArgsConstructor
public class Author {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id ;

     @NotNull
     private String name;

//     @ManyToMany(mappedBy = "authors")
//     private Set<Book> books;

     public Author(String name) {
          this.name = name;
     }
}
