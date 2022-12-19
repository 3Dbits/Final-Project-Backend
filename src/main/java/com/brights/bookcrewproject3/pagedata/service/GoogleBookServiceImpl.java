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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    public List<Book> findBooksByTitle(String title) throws IOException {
        String url2 = url + "intitle:" + title.replace(" ", "+") + "&key=" + googleAPI;

        ObjectMapper om = new ObjectMapper();

        Root root = om.readValue(new URL(url2), Root.class);
        return getBookFromRoot(root);
    }

    @Override
    public List<Book> findBooksByAuthor(String author) throws IOException {
        String url2 = url + "inauthor:" + author + "&key=" + googleAPI;

        ObjectMapper om = new ObjectMapper();

        Root root = om.readValue(new URL(url2), Root.class);
        return getBookFromRoot(root);
    }

    @Override
    public List<Book> findBooksByIsbn2(String isbn) throws IOException {
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

    public List<Book> getBookFromRoot(Root root) {
        List<Book> books = new ArrayList<>();

        for (int position = 0; position < root.getItems().size(); position++) {
            Set<Author> author = new HashSet<>();
            if (root.getItems().get(position).getVolumeInfo().authors != null) {
                for (var e : root.getItems().get(position).getVolumeInfo().authors) {
                    if (authorRepository.existsAuthorByName(e)) {
                        author.add(authorRepository.findAuthorByName(e).orElseThrow());
                    } else {
                        author.add(authorService.saveAuthor(new Author(e)));
                    }
                }
            } else {
                author.add(authorService.saveAuthor(new Author("none")));
            }

            Set<Category> categories = new HashSet<>();
            if (root.getItems().get(position).getVolumeInfo().categories != null) {
                for (var e : root.getItems().get(position).getVolumeInfo().categories) {

                    if (categoryRepository.existsCategoryByGenre(e)) {
                        categories.add(categoryRepository.findCategoryByGenre(e).orElseThrow());
                    } else {
                        categories.add(categoryService.saveCategory(new Category(e)));
                    }
                }
            } else {
                categories.add(categoryService.saveCategory(new Category("none")));
            }

            Book book = new Book(
                    root.getItems().get(position).getVolumeInfo().title,
                    root.getItems().get(position).getVolumeInfo().publisher,
                    root.getItems().get(position).getVolumeInfo().language,
                    root.getItems().get(position).getAccessInfo().getPdf().isAvailable,
                    root.getItems().get(position).getAccessInfo().getPdf().acsTokenLink,
                    root.getItems().get(position).getVolumeInfo().getImageLinks() == null ? "none" : root.getItems().get(position).getVolumeInfo().getImageLinks().smallThumbnail,
                    root.getItems().get(position).getVolumeInfo().getImageLinks() == null ? "none" : root.getItems().get(position).getVolumeInfo().getImageLinks().thumbnail,
                    root.getItems().get(position).getVolumeInfo().publishedDate,
                    root.getItems().get(position).getVolumeInfo().pageCount,
                    root.getItems().get(position).getVolumeInfo().description,
                    categories,
                    author,
                    root.getItems().get(position).getVolumeInfo().getIndustryIdentifiers().get(0).identifier);

            books.add(book);
        }
        return books;
    }
}
