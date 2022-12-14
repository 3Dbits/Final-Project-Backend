package com.brights.bookcrewproject3.security.repository;

import com.brights.bookcrewproject3.security.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo , Long> {
    List<UserInfo> findByBookId(Long bookId);
    List<UserInfo> findByFirstName(String first_name);
}
