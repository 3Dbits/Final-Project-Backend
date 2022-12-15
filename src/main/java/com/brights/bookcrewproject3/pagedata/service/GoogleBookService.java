package com.brights.bookcrewproject3.pagedata.service;

import com.brights.bookcrewproject3.pagedata.model.googlebook.Root;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public interface GoogleBookService {
    Root findBooksByTitle(String title) throws IOException;
    Root findBooksByAuthor(String author) throws IOException;
    Root findBooksByIsbn(String isbn) throws IOException;
}
