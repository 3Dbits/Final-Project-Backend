package com.brights.bookcrewproject3.pagedata.model;

import javax.persistence.*;

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
