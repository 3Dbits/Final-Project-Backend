package com.brights.bookcrewproject3.pagedata.service;


import com.brights.bookcrewproject3.pagedata.model.Book;

import java.util.List;

public interface BookService {

    List<Book> getAllBook();
    Book saveBook(Book book);
    Book getBookById(long id);
    void deleteBookById(long id);

}
