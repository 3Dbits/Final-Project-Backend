package com.brights.bookcrewproject3.pagedata.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Set;


@Entity
@Table (name = "comments")
@Data
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id ;

    @Lob
    @NotEmpty(message = "Comment content can not be empty!")
    private String content;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private LocalDateTime creationDate;


    //@NotNull
    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;

    //@NotNull
    @ManyToOne
    @JoinColumn(name ="user_id", referencedColumnName = "id")
    private UserInfo user;

    @ManyToOne(fetch = FetchType.EAGER, optional=true)
    @JoinColumn(name="parent_id")
    private Comment parent;

    @OneToMany(mappedBy="parent", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
    private Set<Comment> children ;

}