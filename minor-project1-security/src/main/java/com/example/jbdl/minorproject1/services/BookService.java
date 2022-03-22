package com.example.jbdl.minorproject1.services;


import com.example.jbdl.minorproject1.models.Author;
import com.example.jbdl.minorproject1.models.Book;
import com.example.jbdl.minorproject1.models.enums.Genre;
import com.example.jbdl.minorproject1.repository.BookRepository;
import com.example.jbdl.minorproject1.requests.BookCreateRequest;
import com.example.jbdl.minorproject1.responses.BookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.auditing.AuditingHandler;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    AuthorService authorService;

    @Autowired
    BookRepository bookRepository;

    public void createBook(BookCreateRequest bookCreateRequest) {

        Author author = bookCreateRequest.toAuthor();
        author = authorService.createOrGetAuthor(author);// Fetch the author or create an author
        Book book = bookCreateRequest.toBook();
        book.setAuthor(author); //attach author to book object
        saveOrUpdateBook(book);

    }

    public List<BookResponse> getBookById(int bookId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        return Collections.singletonList(book.toBookResponse());
    }

    public List<BookResponse> getBooksByGenre(String genre)
    {
            return  bookRepository.getBookByGenreSql(genre).stream().map(Book :: toBookResponse).collect(Collectors.toList());
    }

    public List<BookResponse> getBooksByAuthorEmail(String authorEmail)
    {

        return bookRepository.findAllByAuthor_Email(authorEmail).stream().map(Book :: toBookResponse).collect(Collectors.toList());
    }

    public List<BookResponse> getBooksByAvailability(boolean avail)
    {
        if(avail)
            return bookRepository.findAllByStudentIsNull().stream().map(Book :: toBookResponse).collect(Collectors.toList());
        else
            return bookRepository.findAllByStudentIsNotNull().stream().map(Book :: toBookResponse).collect(Collectors.toList());
    }

    public Book saveOrUpdateBook(Book book)
    {
        return bookRepository.save(book);
    }
}
