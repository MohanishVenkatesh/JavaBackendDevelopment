package com.example.jbdl.minorproject1.controllers;


import com.example.jbdl.minorproject1.models.Author;
import com.example.jbdl.minorproject1.models.Book;
import com.example.jbdl.minorproject1.requests.BookCreateRequest;
import com.example.jbdl.minorproject1.requests.BookFilterQuery;
import com.example.jbdl.minorproject1.responses.BookResponse;
import com.example.jbdl.minorproject1.services.AuthorService;
import com.example.jbdl.minorproject1.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

//    @GetMapping("/book/{bookId}")
//    public Book getBook(@PathVariable("bookId") int bookId)
//    {
//        return bookService.getBookById(bookId);
//    }

    @GetMapping("/book")
    public List<BookResponse> getBook(@RequestParam("filterType") String filterType,
                                      @RequestParam("filterValue") String filterValue)
    {
        switch(BookFilterQuery.valueOf(filterType))
        {
            case ID:
                return bookService.getBookById(Integer.parseInt(filterValue));

            case AUTHOR:
                return  bookService.getBooksByAuthorEmail(filterValue);

            case GENRE:
                return bookService.getBooksByGenre(filterValue);

            case AVAILABILITY:
                return bookService.getBooksByAvailability(Boolean.valueOf(filterValue));

        }
        return null; //TODO: NEED TO CHANGE THE RETURN LATER.
    }

    // 1. Get all the books which are written by x author
    //2. Get all the books of genre x
    // 3. Get all available books
    // 4 . Get the book given a book id

}
