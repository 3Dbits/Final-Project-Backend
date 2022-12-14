package com.brights.bookcrewproject3.security.repository;

import com.brights.bookcrewproject3.security.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
