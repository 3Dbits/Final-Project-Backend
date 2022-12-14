package com.brights.bookcrewproject3.security.service;

import com.brights.bookcrewproject3.security.model.Book;
import com.brights.bookcrewproject3.security.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book getBooksById(long id) {
        Optional<Book> optional = this.bookRepository.findById(id);
        Book book = null;

        if (optional.isPresent()) {
            book = optional.get();
        } else {
            throw new IllegalStateException("Book with id " + id + " was not found.");
        }

        return book;
    }

    @Override
    public void deleteBookById(long id) {

    }
}

