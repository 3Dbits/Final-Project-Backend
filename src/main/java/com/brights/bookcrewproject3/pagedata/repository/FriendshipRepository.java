package com.brights.bookcrewproject3.pagedata.repository;

import com.brights.bookcrewproject3.pagedata.model.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
    Optional<Friendship> findById(Long id);
    @Transactional
    void deleteById(Long id);
}