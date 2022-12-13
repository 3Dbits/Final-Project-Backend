package com.brights.bookcrewproject3.security.model;

import com.brights.bookcrewproject3.BookcrewProject3Application;
import lombok.Data;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.awt.print.Book;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table( name = "user_info")
@Data
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "first_name")
    public String first_name ;

    @NotBlank
    @Column(name="last_name")
    public String last_name ;

    @NotBlank
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="signup_date")
    public Date signup_date ; // MS if we dont need this - move ( bonus )

    @NotBlank
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name="favorite_bookID")
    private Long bookId ;

    @OneToMany(mappedBy = "userInfo")   //onetomany as one user can have many posts
    private List<Post> posts;

    @OneToMany(mappedBy = "userInfo")
    private Set<Friendship> friends ;





}
