package com.brights.bookcrewproject3.pagedata.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table( name = "user_info")
@Data
@NoArgsConstructor
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "first_name")
    public String firstName ;

    @NotBlank
    @Column(name="last_name")
    public String lastName ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="signup_date")
    public Date signupSate ; // MS if we dont need this - move ( bonus )

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name="favorite_bookID")
    private Long bookId ;

    @OneToMany(mappedBy = "userInfo")   //onetomany as one user can have many posts
    private List<Post> posts;

    @OneToMany(mappedBy = "userInfo")
    private Set<Friendship> friends ;

    public UserInfo(String firstName, String lastName, Date signupSate, LocalDate dateOfBirth, Long bookId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.signupSate = signupSate;
        this.dateOfBirth = dateOfBirth;
        this.bookId = bookId;
    }
}
