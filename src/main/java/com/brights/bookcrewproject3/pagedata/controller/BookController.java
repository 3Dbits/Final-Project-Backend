package com.brights.bookcrewproject3.pagedata.controller;

import com.brights.bookcrewproject3.pagedata.model.Book;
import com.brights.bookcrewproject3.pagedata.model.googlebook.Root;
import com.brights.bookcrewproject3.pagedata.service.GoogleBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@CrossOrigin(origins = "*", maxAge = 3600) //MC all urls can access this controller
@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private GoogleBookService googleBookService;

    @GetMapping("/search")
    public ResponseEntity<Book> searchBook(@RequestParam(value = "bookName", required = false, defaultValue = "") String bookName,
                                           @RequestParam(value = "bookAuthor", required = false, defaultValue = "") String bookAuthor,
                                           @RequestParam(value = "bookIsbn", required = false, defaultValue = "") String bookIsbn) {
        Book resolute = null;

        try {
            if (!bookName.isEmpty()) {
                resolute = googleBookService.findBooksByTitle(bookName);
            } else if (!bookAuthor.isEmpty()) {
                resolute = googleBookService.findBooksByAuthor(bookAuthor);
            } else if (!bookIsbn.isEmpty()) {
                resolute = googleBookService.findBooksByIsbn2(bookIsbn);
            }

            return resolute == null ? new ResponseEntity<>( null, HttpStatus.BAD_REQUEST) : new ResponseEntity<>(resolute, HttpStatus.OK);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
