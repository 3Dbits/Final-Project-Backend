package com.brights.bookcrewproject3.pagedata.repository;

import com.brights.bookcrewproject3.pagedata.model.Post;
import com.brights.bookcrewproject3.pagedata.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByUserInfo(UserInfo userInfo);

}

