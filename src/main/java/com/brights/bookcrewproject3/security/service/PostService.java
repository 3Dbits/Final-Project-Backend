package com.brights.bookcrewproject3.security.service;

import com.brights.bookcrewproject3.security.model.Post;

import java.util.List;

public interface PostService {
    List<Post> getAllPosts();

    void savePost(Post post);

    Post getPostById(long id);

    void deletePostById(long id);
}
