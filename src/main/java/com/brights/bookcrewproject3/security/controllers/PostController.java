package com.brights.bookcrewproject3.security.controllers;
import com.brights.bookcrewproject3.security.model.Post;
import com.brights.bookcrewproject3.security.service.BookService;
import com.brights.bookcrewproject3.security.service.UserService;
import com.brights.bookcrewproject3.security.service.CommentService;
import com.brights.bookcrewproject3.security.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;

@Controller
public class PostController {

    private BookService bookService;
    private UserService userService;
    private PostService postService;
    private CommentService commentService;
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/uploads";

    @Autowired
    public PostController(BookService bookService, UserService userService, PostService postService, CommentService commentService) {
        this.bookService = bookService;
        this.userService = userService;
        this.postService = postService;
        this.commentService = commentService;
    }

    @GetMapping("/post/new")
    public String postNewForm(Model model) {
        model.addAttribute("post", new Post());

        return "/post/addNew";
    }

    @PostMapping("/post/new")
    public String postNewSave(@Valid @ModelAttribute Post post,
                              BindingResult bindingResult,
                              Model model,
                              @RequestParam(value = "image", required = false) MultipartFile file,
                              Principal principal) throws IOException {
        if(bindingResult.hasErrors()){
            return "/post/addNew";
        }

        if (!file.isEmpty()) {
            StringBuilder fileNames = new StringBuilder();
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
            fileNames.append(file.getOriginalFilename());
            Files.write(fileNameAndPath, file.getBytes());
            post.setPathOfPicture("/uploads/" + file.getOriginalFilename());
        }
        post.setUser(bookService.getUserById(userService.getDetails(principal.getName()).getId()));

        postService.savePost(post);

        return "redirect:/";
    }
////////////

    //forma za pisanje komentara
    @PostMapping("/post/comment")
    public String saveComment(@Valid @ModelAttribute Comment comment,
                              BindingResult bindingComment,
                              @RequestParam(value = "idpost") long id,
                              @RequestParam(value = "username") String username)  {
        System.err.println("POST comment: " + comment);
        if (bindingComment.hasErrors()) {
            System.err.println("Comment did not validate");
            return "/post/postCommentForm";
        } else {
            comment.setPost(postService.getPostById(id));
            comment.setUser(bookService.getUserById(userService.getDetails(username).getUser().getId()));
            this.commentService.saveComment(comment);
            System.err.println("SAVE comment: " + comment);
            return "redirect:/post/" + comment.getPost().getId();
        }
    }

    //kreiramo novi komentar -- spojen na post/Comment
    // njezin submit bi trebao pokrenuti akciju za postMapping post/comment
    @GetMapping("/showCommentForUpdate/{id}") //ovaj id je id od POSTa
    public String commentForm(@PathVariable(value = "id") long id, Model model, Principal principal){

        Post post = postService.getPostById(id);
        model.addAttribute("post", post);
        model.addAttribute("comment", new Comment());
        model.addAttribute("username", principal.getName());
        return "/post/postCommentForm";
    }


    //vidjeti post po njegovom IDu i sve njegove komentare
    @GetMapping("/post/{id}")
    public String viewPost(Model model,
                           @PathVariable(value = "id")long id,
                           Principal principal) {

        model.addAttribute("post", postService.getPostById(id));
        model.addAttribute("comment", commentService.getAllCommentByPostId(id));
        model.addAttribute("userCred", userService.getDetails(principal.getName()));
        model.addAttribute("newComment", new Comment());
        return "/post/postView";
    }



    @GetMapping("/showFormForPostDelete/{id}")
    public String deletePost(@ModelAttribute Post post,
                             @PathVariable(value = "id") long id){

        postService.deletePostById(id);
        return "redirect:/";
    }

    @GetMapping("/postList")
    public String postList(Model model){
        model.addAttribute("postList", postService.getAllPosts());
        return "/post/listPost";
    }

////Update Post


    @GetMapping("/updatePost/{id}")
    public String updateNewForm(Model model,
                                @PathVariable(value="id") long id) {
        model.addAttribute("post", postService.getPostById(id));

        return "/post/updatePost";
    }


    @PostMapping("/updatePost/{id}")
    public String updatePost(/*@Valid*/ @ModelAttribute Post post,
                                        BindingResult bindingResult,
                                        Model model,
                                        @PathVariable(value="id") long id)  {
        if(bindingResult.hasErrors()){

            return "/post/updatePost";
        }
        Post postOld = postService.getPostById(id);
        postOld.setTitle(post.getTitle());
        postOld.setSummary(post.getSummary());
        postOld.setContent(post.getContent());
        postService.savePost(postOld);

        return "redirect:/";
    }
}
