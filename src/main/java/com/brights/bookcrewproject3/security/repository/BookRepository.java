package com.brights.bookcrewproject3.security.repository;

import com.brights.bookcrewproject3.security.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}

