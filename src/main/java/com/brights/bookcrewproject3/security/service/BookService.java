package com.brights.bookcrewproject3.security.service;

import java.awt.print.Book;
import java.util.List;

public interface BookService {

    List<Book> getAllBook();
    Book saveBook(Book book);
    Book getBookById(long id);
    void deleteBookById(long id);

}
