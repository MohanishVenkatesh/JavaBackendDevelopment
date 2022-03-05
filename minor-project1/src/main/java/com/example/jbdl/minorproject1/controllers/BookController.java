package com.example.jbdl.minorproject1.controllers;


import com.example.jbdl.minorproject1.models.Author;
import com.example.jbdl.minorproject1.models.Book;
import com.example.jbdl.minorproject1.requests.BookCreateRequest;
import com.example.jbdl.minorproject1.services.AuthorService;
import com.example.jbdl.minorproject1.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class BookController {
    @Autowired
    BookService bookService;


    @PostMapping("/book")
    public ResponseEntity createBook(@Valid @RequestBody BookCreateRequest bookCreateRequest)
    {
        bookService.createBook(bookCreateRequest);
        return new ResponseEntity(HttpStatus.OK);

    }

    @GetMapping("/book/{bookId}")
    public Book getBook(@PathVariable("bookId") int bookId)
    {
        return bookService.getBookById(bookId);
    }
}
