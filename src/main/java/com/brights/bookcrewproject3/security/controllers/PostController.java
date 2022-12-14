package com.brights.bookcrewproject3.security.controllers;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {

//    private BookService bookService;
//    private UserInfoService userInfoService;
//    private PostService postService;
//    private CommentService commentService;
//
//    @Autowired
//    public PostController(BookService bookService, UserInfoService userInfoService, PostService postService, CommentService commentService) {
//        this.bookService = bookService;
//        this.userInfoService = userInfoService;
//        this.postService = postService;
//        this.commentService = commentService;
//    }

//    @GetMapping("/post/new")
//    public String postNewForm(Model model) {
//        model.addAttribute("post", new Post());
//
//        return "/post/addNew";
//    }
//
//    @PostMapping("/post/new")
//    public String postNewSave(@Valid @ModelAttribute Post post,
//                              BindingResult bindingResult,
//                              Model model,
//                              Principal principal) {
//        if(bindingResult.hasErrors()){
//            return "/post/addNew";
//        }
//
//
//        postService.savePost(post);
//
//        return "redirect:/";
//    }
//
//    @PostMapping("/post/comment")
//    public String saveComment(@Valid @ModelAttribute Comment comment,
//                              BindingResult bindingComment,
//                              @RequestParam(value = "idpost") long id,
//                              @RequestParam(value = "username") String username)  {
//        System.err.println("POST comment: " + comment);
//        if (bindingComment.hasErrors()) {
//            System.err.println("Comment did not validate");
//            return "/post/postCommentForm";
//        } else {
//            comment.setPost(postService.getPostById(id));
////            comment.setUser(bookService.getUserById(userService.getDetails(username).getUser().getId()));
////            this.commentService.saveComment(comment);
//            System.err.println("SAVE comment: " + comment);
//            return "redirect:/post/" + comment.getPost().getId();
//        }
//    }
//
//    //kreiramo novi komentar -- spojen na post/Comment
//    // njezin submit bi trebao pokrenuti akciju za postMapping post/comment
//    @GetMapping("/showCommentForUpdate/{id}") //ovaj id je id od POSTa
//    public String commentForm(@PathVariable(value = "id") long id, Model model, Principal principal){
//
//        Post post = postService.getPostById(id);
//        model.addAttribute("post", post);
//        model.addAttribute("comment", new Comment());
//        model.addAttribute("username", principal.getName());
//        return "/post/postCommentForm";
//    }
//
//
//    //vidjeti post po njegovom IDu i sve njegove komentare
//    @GetMapping("/post/{id}")
//    public String viewPost(Model model,
//                           @PathVariable(value = "id")long id,
//                           Principal principal) {
//
//        model.addAttribute("post", postService.getPostById(id));
////        model.addAttribute("comment", commentService.getAllCommentByPostId(id));
////        model.addAttribute("userCred", userService.getDetails(principal.getName()));
//        model.addAttribute("newComment", new Comment());
//        return "/post/postView";
//    }
//
//
//
//    @GetMapping("/showFormForPostDelete/{id}")
//    public String deletePost(@ModelAttribute Post post,
//                             @PathVariable(value = "id") long id){
//
//        postService.deletePostById(id);
//        return "redirect:/";
//    }
//
//    @GetMapping("/postList")
//    public String postList(Model model){
//        model.addAttribute("postList", postService.getAllPosts());
//        return "/post/listPost";
//    }
//
//////Update Post
//
//
//    @GetMapping("/updatePost/{id}")
//    public String updateNewForm(Model model,
//                                @PathVariable(value="id") long id) {
//        model.addAttribute("post", postService.getPostById(id));
//
//        return "/post/updatePost";
//    }
//
//
//    @PostMapping("/updatePost/{id}")
//    public String updatePost(/*@Valid*/ @ModelAttribute Post post,
//                                        BindingResult bindingResult,
//                                        Model model,
//                                        @PathVariable(value="id") long id)  {
//        if(bindingResult.hasErrors()){
//
//            return "/post/updatePost";
//        }
//        Post postOld = postService.getPostById(id);
//        postOld.setTitle(post.getTitle());
//        postOld.setSummary(post.getSummary());
//        postOld.setContent(post.getContent());
//        postService.savePost(postOld);
//
//        return "redirect:/";
//    }
}
