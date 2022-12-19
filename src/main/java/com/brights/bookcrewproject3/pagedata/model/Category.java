package com.brights.bookcrewproject3.pagedata.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private String genre;

//    @ManyToMany(mappedBy = "categories")
//    private Set<Book> books;

    public Category(String genre) {
        this.genre = genre;
    }
}
