package com.brights.bookcrewproject3.pagedata.service;

import com.brights.bookcrewproject3.pagedata.model.Book;
import com.brights.bookcrewproject3.pagedata.model.googlebook.Root;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public interface GoogleBookService {
    List<Book> findBooksByTitle(String title) throws IOException;
    List<Book> findBooksByAuthor(String author) throws IOException;
    Root findBooksByIsbn(String isbn) throws IOException;
    List<Book> findBooksByIsbn2(String isbn) throws IOException;
}
