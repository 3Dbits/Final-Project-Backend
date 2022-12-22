package com.brights.bookcrewproject3.pagedata.service;

import com.brights.bookcrewproject3.pagedata.model.Book;
import com.brights.bookcrewproject3.pagedata.model.Post;

import java.io.IOException;
import java.util.List;

public interface PostService {
    List<Post> getAllPosts();

    Post savePost(Post post, String bookIsbn, String username) throws IOException;

    Post getPostById(long id);

    void deletePostById(long id);

    List<Post> getAllPostsByUsername(String username);
    List<Post> getAllPostsByUsername2(String username);

    void likePost(long postId);
    void dislikePost(long postId);
}
