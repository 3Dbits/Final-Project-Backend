package com.brights.bookcrewproject3.pagedata.service;

import com.brights.bookcrewproject3.pagedata.model.Comment;

public interface CommentService {
    Comment save (Comment comment);
    void delete(Comment comment);

    Comment getCommentById (Long id);

    void deleteCommentById (Long id);

    Comment createCommentOnPost(long idPost, Comment comment, String username);

    Comment createCommentOnComment(long idPost, long idComment, Comment comment, String username);
}
