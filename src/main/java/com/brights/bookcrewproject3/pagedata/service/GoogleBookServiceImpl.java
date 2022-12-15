package com.brights.bookcrewproject3.pagedata.service;

import com.brights.bookcrewproject3.pagedata.model.googlebook.Root;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Service
public class GoogleBookServiceImpl implements GoogleBookService {

    // GET https://www.googleapis.com/books/v1/volumes?q=flowers+inauthor:keyes&key=yourAPIKey
    // book.app.googleAPIKey= AIzaSyCOoa7REJqyCeuVfs581BNnVlp36op0JYI
    @Value("${book.app.googleAPIKey}")
    private String googleAPI;
    private String url = "https://www.googleapis.com/books/v1/volumes?q=";

    @Override
    public Root findBooksByTitle(String title) throws IOException {
        String url2 = url + "intitle:" + title + "&key=" + googleAPI;

        ObjectMapper om = new ObjectMapper();

        return om.readValue(new URL(url2), Root.class);
    }

    @Override
    public Root findBooksByAuthor(String author) throws IOException {
        String url2 = url + "inauthor:" + author + "&key=" + googleAPI;

        ObjectMapper om = new ObjectMapper();

        return om.readValue(new URL(url2), Root.class);
    }

    @Override
    public Root findBooksByIsbn(String isbn) throws IOException {
        String url2 = url + "isbn:" + isbn + "&key=" + googleAPI;

        ObjectMapper om = new ObjectMapper();

        return om.readValue(new URL(url2), Root.class);
    }
}
