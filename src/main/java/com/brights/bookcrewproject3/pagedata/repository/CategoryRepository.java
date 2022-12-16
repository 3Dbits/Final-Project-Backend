package com.brights.bookcrewproject3.pagedata.repository;

import com.brights.bookcrewproject3.pagedata.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsCategoryByGenre(String e);

    Optional<Category> findCategoryByGenre(String e);
}
