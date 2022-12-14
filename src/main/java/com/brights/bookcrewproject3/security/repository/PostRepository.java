package com.brights.bookcrewproject3.security.repository;

import com.brights.bookcrewproject3.security.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}

