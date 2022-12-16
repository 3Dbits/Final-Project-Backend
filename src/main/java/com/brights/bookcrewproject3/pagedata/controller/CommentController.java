package com.brights.bookcrewproject3.pagedata.controller;

import com.brights.bookcrewproject3.pagedata.model.Comment;
import com.brights.bookcrewproject3.pagedata.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600) //MC all urls can access this controller
@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PutMapping("/{idPost}")
    public ResponseEntity<Comment> createComment(@PathVariable(value = "id") long idPost,
                                                 @RequestParam(value = "idComment", required = false) long idComment,
                                                 @RequestBody Comment comment,
                                                 Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        if (idComment == 0) {
            return new ResponseEntity<>(commentService.createCommentOnPost(idPost, comment, userDetails.getUsername()), HttpStatus.OK);
        }

        return new ResponseEntity<>(commentService.createCommentOnComment(idPost,idComment,comment, userDetails.getUsername()), HttpStatus.OK);
    }
}
