package com.brights.bookcrewproject3.pagedata.service;

import com.brights.bookcrewproject3.pagedata.model.Author;
import com.brights.bookcrewproject3.pagedata.model.Book;
import com.brights.bookcrewproject3.pagedata.model.Category;
import com.brights.bookcrewproject3.pagedata.model.googlebook.Root;
import com.brights.bookcrewproject3.pagedata.repository.AuthorRepository;
import com.brights.bookcrewproject3.pagedata.repository.CategoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

@Service
public class GoogleBookServiceImpl implements GoogleBookService {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryService categoryService;

    // GET https://www.googleapis.com/books/v1/volumes?q=flowers+inauthor:keyes&key=yourAPIKey
    // book.app.googleAPIKey= AIzaSyCOoa7REJqyCeuVfs581BNnVlp36op0JYI
    @Value("${book.app.googleAPIKey}")
    private String googleAPI;
    private String url = "https://www.googleapis.com/books/v1/volumes?q=";

    @Override
    public Book findBooksByTitle(String title) throws IOException {
        String url2 = url + "intitle:" + title + "&key=" + googleAPI;

        ObjectMapper om = new ObjectMapper();

        Root root = om.readValue(new URL(url2), Root.class);
        return getBookFromRoot(root);
    }

    @Override
    public Book findBooksByAuthor(String author) throws IOException {
        String url2 = url + "inauthor:" + author + "&key=" + googleAPI;

        ObjectMapper om = new ObjectMapper();

        Root root = om.readValue(new URL(url2), Root.class);
        return getBookFromRoot(root);
    }

    @Override
    public Book findBooksByIsbn2(String isbn) throws IOException {
        String url2 = url + "isbn:" + isbn + "&key=" + googleAPI;

        ObjectMapper om = new ObjectMapper();

        Root root = om.readValue(new URL(url2), Root.class);
        return getBookFromRoot(root);
    }
    @Override
    public Root findBooksByIsbn(String isbn) throws IOException {
        String url2 = url + "isbn:" + isbn + "&key=" + googleAPI;

        ObjectMapper om = new ObjectMapper();

        return om.readValue(new URL(url2), Root.class);
    }

    public Book getBookFromRoot(Root root) {
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

        Book book = new Book(
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
                        author);

        return book;
    }
}
