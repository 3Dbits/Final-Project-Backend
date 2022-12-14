package com.brights.bookcrewproject3.security.model;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table ( name = "friends")
public class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name ="userInfo_id", referencedColumnName = "id",nullable = false)
    private UserInfo userInfo;


}
