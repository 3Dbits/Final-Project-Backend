package com.brights.bookcrewproject3.security.model;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "categories")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String genre;

    @ManyToMany(mappedBy = "categories")
    private Set<Book> books;


}
