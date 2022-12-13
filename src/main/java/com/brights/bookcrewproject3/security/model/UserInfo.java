package com.brights.bookcrewproject3.security.model;

import com.brights.bookcrewproject3.BookcrewProject3Application;
import lombok.Data;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @NotNull
    @Column(name = "first_name")
    public String first_name ;

    @NotNull
    @Column(name="last_name")
    public String last_name ;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="signup_date")
    public Date signup_date ; // MS if we dont need this - move ( bonus )

    @NotNull
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
