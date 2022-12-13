package com.brights.bookcrewproject3.security.model;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table ( name = "friends")
public class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @Column(name = "user_id")
    private Long user_id;

    @NotBlank
    @Column(name = "friend_id")
    private Long friend_id;

}
