package com.example.jbdl.minorproject1.services;


import com.example.jbdl.minorproject1.models.Author;
import com.example.jbdl.minorproject1.models.Book;
import com.example.jbdl.minorproject1.repository.BookRepository;
import com.example.jbdl.minorproject1.requests.BookCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.auditing.AuditingHandler;
import org.springframework.stereotype.Service;

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
        bookRepository.save(book);

    }

    public Book getBookById(int bookId) {

        return bookRepository.findById(bookId).orElse(null);
    }
}
