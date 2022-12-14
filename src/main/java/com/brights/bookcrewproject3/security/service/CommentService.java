package com.brights.bookcrewproject3.security.service;

import com.brights.bookcrewproject3.security.model.Comment;

public interface CommentService {
    Comment save (Comment comment);
    void delete(Comment comment);

    Comment getCommentById (Long id);

    void deleteCommentById (Long id);
}
