package com.brights.bookcrewproject3.pagedata.service;

import com.brights.bookcrewproject3.pagedata.model.Comment;
import com.brights.bookcrewproject3.pagedata.model.Post;
import com.brights.bookcrewproject3.pagedata.repository.CommentRepository;
import com.brights.bookcrewproject3.pagedata.repository.PostRepository;
import com.brights.bookcrewproject3.security.model.User;
import com.brights.bookcrewproject3.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostService postService;



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

    @Override
    public Comment createCommentOnPost(long idPost, Comment comment, String username) {
        User user = userRepository.findByUsername(username).orElseThrow();

        Comment comment1 = new Comment();
        comment1.setUser(user.getUserInfo());
        comment1.setCreationDate(LocalDateTime.now());
        comment1.setContent(comment.getContent());
        Comment _comment = commentRepository.save(comment1);

        Post post = postService.getPostById(idPost);
        List<Comment> comments = post.getComments();
        comments.add(_comment);
        post.setComments(comments);
        post.setContent("TesttttSpremanja");
        postRepository.save(post);

//        Post posttest = postService.getPostById(idPost);

        return _comment;
    }

    @Override
    public Comment createCommentOnComment(long idPost, long idComment, Comment comment, String username) {
        User user = userRepository.findByUsername(username).orElseThrow();

        Comment comment1 = new Comment();
        comment1.setUser(user.getUserInfo());
        comment1.setCreationDate(LocalDateTime.now());
        comment1.setContent(comment.getContent());
        Comment parentComment = commentRepository.findById(idComment).get();
        comment1.setParent(parentComment);
        Comment _comment = commentRepository.save(comment1);

        Set<Comment> comments = parentComment.getChildren();
        comments.add(_comment);
        parentComment.setChildren(comments);
        commentRepository.save(parentComment);

        //post loop should be added

        return _comment;
    }
}
