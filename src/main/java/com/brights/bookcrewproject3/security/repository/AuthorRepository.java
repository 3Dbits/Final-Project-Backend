package com.brights.bookcrewproject3.security.repository;

import com.brights.bookcrewproject3.security.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findById (Long id);
    List<Author> findByFirstNameContainingIgnoreCase(String firstName);
    List<Author> findByLastNameContainingIgnoreCase(String lastName);

    @Transactional
    void deleteById(Long id);
}
