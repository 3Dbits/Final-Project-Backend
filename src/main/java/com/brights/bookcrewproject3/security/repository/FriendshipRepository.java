package com.brights.bookcrewproject3.security.repository;

import com.brights.bookcrewproject3.security.model.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
    Optional<Friendship> findById(Long id);

    @Transactional
    void deleteById(Long id);
}