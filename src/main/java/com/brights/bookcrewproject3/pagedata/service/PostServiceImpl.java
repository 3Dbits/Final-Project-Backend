package com.brights.bookcrewproject3.pagedata.service;

import com.brights.bookcrewproject3.pagedata.model.*;
import com.brights.bookcrewproject3.pagedata.model.googlebook.Root;
import com.brights.bookcrewproject3.pagedata.repository.*;
import com.brights.bookcrewproject3.security.model.User;
import com.brights.bookcrewproject3.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class PostServiceImpl implements PostService{

    private PostRepository postRepository;
    private UserRepository userRepository;
    private UserInfoRepository userInfoRepository;
    private GoogleBookService googleBookService;
    private CategoryService categoryService;
    private AuthorService authorService;
    private BookService bookService;
    private AuthorRepository authorRepository;
    private CategoryRepository categoryRepository;
    private FriendshipRepository friendshipRepository;
    private UserInfoService userInfoService;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserInfoService userInfoService, FriendshipRepository friendshipRepository, UserRepository userRepository, UserInfoRepository userInfoRepository, GoogleBookService googleBookService, CategoryService categoryService, AuthorService authorService, BookService bookService, AuthorRepository authorRepository, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.userInfoRepository = userInfoRepository;
        this.googleBookService = googleBookService;
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
        this.friendshipRepository = friendshipRepository;
        this.userInfoService = userInfoService;
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post savePost(Post post, String bookIsbn, String username) throws IOException {
        User user = userRepository.findByUsername(username).orElseThrow();
//        UserInfo userInfo = userInfoRepository.findUserInfoById(user.get().getUserInfo().getId());

        Root root = googleBookService.findBooksByIsbn(bookIsbn);

        Set<Author> author = new HashSet<>();
        for (var e : root.getItems().get(0).getVolumeInfo().authors) {
            if (authorRepository.existsAuthorByName(e)) {
                author.add(authorRepository.findAuthorByName(e).orElseThrow());
            } else {
                author.add(authorService.saveAuthor(new Author(e)));
            }
        }

        Set<Category> categories = new HashSet<>();
        for (var e : root.getItems().get(0).getVolumeInfo().categories) {
            if (categoryRepository.existsCategoryByGenre(e)) {
                categories.add(categoryRepository.findCategoryByGenre(e).orElseThrow());
            } else {
                categories.add(categoryService.saveCategory(new Category(e)));
            }
        }

        Book book = bookService.saveBook(
                new Book(
                        root.getItems().get(0).getVolumeInfo().title,
                        root.getItems().get(0).getVolumeInfo().publisher,
                        root.getItems().get(0).getVolumeInfo().language,
                        root.getItems().get(0).getAccessInfo().getPdf().isAvailable,
                        root.getItems().get(0).getAccessInfo().getPdf().acsTokenLink,
                        root.getItems().get(0).getVolumeInfo().getImageLinks().smallThumbnail,
                        root.getItems().get(0).getVolumeInfo().getImageLinks().thumbnail,
                        root.getItems().get(0).getVolumeInfo().publishedDate,
                        root.getItems().get(0).getVolumeInfo().pageCount,
                        root.getItems().get(0).getVolumeInfo().description,
                        categories,
                        author,
                        root.getItems().get(0).getVolumeInfo().getIndustryIdentifiers().get(0).identifier)
        );

        post.setBook(book);
        post.setUserInfo(user.getUserInfo());
        post.setCreateAt(new Date());
        post.setUpdateAt(new Date());
        return postRepository.save(post);
    }

    @Override
    public Post getPostById(long id) {
        Optional<Post> optional = this.postRepository.findById(id);
        Post post = null;

        if (optional.isPresent()) {
            post = optional.get();
        } else {
            throw new IllegalStateException("Post with id " + id + " was not found.");
        }

        return post;
    }

    @Override
    public void deletePostById(long id) {
        boolean exists = this.postRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Post with id " + id + " was not found.");
        }
        this.postRepository.deleteById(id);
    }

    @Override
    public List<Post> getAllPostsByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();

        List<Post> newList = new ArrayList<>(postRepository.findAllByUserInfo(user.getUserInfo()));

        List<UserInfo> userInfos = userInfoService.getAllFriends(username);
        for (var e : userInfos) {
            newList.addAll(postRepository.findAllByUserInfo(e));
        }

        return newList;
    }

    @Override
    public List<Post> getAllPostsByUsername2(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();

        return new ArrayList<>(postRepository.findAllByUserInfo(user.getUserInfo()));
    }
}
