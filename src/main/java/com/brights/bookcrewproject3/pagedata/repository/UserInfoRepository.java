package com.brights.bookcrewproject3.pagedata.repository;

import com.brights.bookcrewproject3.pagedata.model.UserInfo;
import com.brights.bookcrewproject3.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo , Long> {
    List<UserInfo> findByBookId(Long bookId);
    List<UserInfo> findByFirstName(String first_name);
    UserInfo findUserInfoById(Long id);
}
