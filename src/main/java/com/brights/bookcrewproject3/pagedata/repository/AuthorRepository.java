package com.brights.bookcrewproject3.pagedata.repository;

import com.brights.bookcrewproject3.pagedata.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findById (Long id);
    @Transactional
    void deleteById(Long id);
    boolean existsAuthorByName(String name);

    Optional<Author> findAuthorByName(String name);
}
