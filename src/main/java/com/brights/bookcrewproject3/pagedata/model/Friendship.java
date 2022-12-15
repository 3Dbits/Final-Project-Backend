package com.brights.bookcrewproject3.pagedata.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table ( name = "friends")
@Data
@NoArgsConstructor
public class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="userinfo_id", nullable=false)
    private UserInfo userInfo;
    private Long friend1_id; //onaj koji pokrene
    private Long friend2_id; //za koga zeli

    public Friendship(UserInfo userInfo, Long friend1_id, Long friend2_id) {
        this.userInfo = userInfo;
        this.friend1_id = friend1_id;
        this.friend2_id = friend2_id;
    }
}
