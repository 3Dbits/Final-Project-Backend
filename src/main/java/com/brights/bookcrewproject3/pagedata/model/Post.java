package com.brights.bookcrewproject3.pagedata.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "posts")
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(min = 2, max = 50, message = "Title should be between 2 and 50 letters")
    private String title;

    @NotNull
    @Size(min = 2, max = 50, message = "MetaTitle should be between 2 and 50 letters")
    private String metaTitle;

    @NotNull
    @Size(min = 2, max = 250, message = "Summary should be between 2 and 250 characters")
    private String summary;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "creation_date")
    private Date createAt;

    private Date updateAt;

    private String pathOfPicture;


    @NotNull
    @Size(min = 2, message = "Content should at least have 2 characters")
    private String content;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id") //veza za userINfo
    private UserInfo userInfo;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL) //veza za Comment
    private Set<Comment> comments;

}
