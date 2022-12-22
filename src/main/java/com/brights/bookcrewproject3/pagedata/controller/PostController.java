package com.brights.bookcrewproject3.pagedata.controller;

import com.brights.bookcrewproject3.pagedata.model.Post;
import com.brights.bookcrewproject3.pagedata.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600) //MC all urls can access this controller
@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostService postService;

    //get All Books from Google API
    @GetMapping("/all")
    public ResponseEntity<List<Post>> getAllPost(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        List<Post> list = postService.getAllPostsByUsername(userDetails.getUsername());

        if (list.isEmpty()) {
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    //Post new post
    @PostMapping("/addNew")
    public ResponseEntity<Post> createNewPost(Authentication authentication,
                                              @RequestBody Post post,
                                              @RequestParam(name = "bookIsbn") String bookIsbn) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        try {
            Post _post = postService.savePost(post, bookIsbn, userDetails.getUsername());

            return new ResponseEntity<>(_post, HttpStatus.CREATED);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Info for one post
    @GetMapping("/{id}")
    public ResponseEntity<Post> getOnePost(Authentication authentication,
                                                 @PathVariable(value = "id") long id) {
        try {
            Post post = postService.getPostById(id);

            return new ResponseEntity<>(post, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/allProfile")
    public ResponseEntity<List<Post>> getAllPostForProfile(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        List<Post> list = postService.getAllPostsByUsername2(userDetails.getUsername());

        if (list.isEmpty()) {
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/like/{id}")
    public ResponseEntity<?> likePost(Authentication authentication,
                                           @PathVariable(value = "id") long id) {

            postService.likePost(id);

            return new ResponseEntity<>(HttpStatus.OK);

    }
    @PostMapping("/dislike/{id}")
    public ResponseEntity<?> dislikePost(Authentication authentication,
                                         @PathVariable(value = "id") long id) {
        postService.dislikePost(id);

            return new ResponseEntity<>(HttpStatus.OK);

    }
}
