package com.brights.bookcrewproject3.security.service;

import com.brights.bookcrewproject3.security.model.Comment;
import com.brights.bookcrewproject3.security.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository ;
    }


    @Override
    public Comment save(Comment comment) {
        comment.setCreationDate(LocalDateTime.now());
        return commentRepository.save(comment);
    }

    @Override
    public void delete(Comment comment) {
        commentRepository.delete(comment);

    }

    @Override
    public Comment getCommentById(Long id) {
        Optional<Comment> optional = commentRepository.findById(id);
        Comment comment = null ;

        if(optional.isPresent()) {
            comment = optional.get();
        }
        return comment;
    }

    @Override
    public void deleteCommentById(Long id) {
        boolean exists = this.commentRepository.existsById(id);
        if(!exists) {
            throw new IllegalStateException("Comment with id" + id + " is not found.");
        }
        this.commentRepository.deleteById(id);

    }
}
