package com.example.jbdl.minorproject1;

import com.example.jbdl.minorproject1.models.Book;
import com.example.jbdl.minorproject1.models.Request;
import com.example.jbdl.minorproject1.models.Student;
import com.example.jbdl.minorproject1.models.Transcation;
import com.example.jbdl.minorproject1.models.enums.RequestType;
import com.example.jbdl.minorproject1.models.enums.TransactionStatus;
import com.example.jbdl.minorproject1.repository.TransactionRepository;
import com.example.jbdl.minorproject1.services.BookService;
import com.example.jbdl.minorproject1.services.RequestService;
import com.example.jbdl.minorproject1.services.TransactionService;
import org.hibernate.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

    @InjectMocks
    TransactionService transactionService;

    @Mock
    BookService bookService;

    @Mock
    TransactionRepository transactionRepository;


    @Mock
    RequestService requestService;

    @Test
    public void createTransactionTest_Issue() throws Exception {
        Book book = Book.builder()
                .id(40000)
                .name("Random book name")
                .build();


        Student student = Student.builder()
                .id(2000)
                .name("Ram")
                .build();
        Book updatedBook = Book.builder()
                .id(40000)
                .name("Random book name")
                .student(student)
                .build();

        Request request = Request.builder()
                .id(10000)
                .requestType(RequestType.ISSUE)
                .my_student(student)
                .book(book)
                .build();


        Transcation transaction = Transcation.builder()
                .id(23)
                .externalTransactionId(UUID.randomUUID().toString())
                .transactionDate(new Date(System.currentTimeMillis()))
                .build();

        when(transactionRepository.save(any())).thenReturn(transaction);
//        when(requestService.getRequestById(request.getId())).thenReturn(requestByService);
        when(bookService.saveOrUpdateBook(any())).thenReturn(updatedBook);
        when(transactionRepository.save(any())).thenReturn(transaction);


        String txnId = transactionService.createTransaction(request);

        verify(bookService, times(1)).saveOrUpdateBook(any());
        verify(transactionRepository, times(2)).save(any());
        verify(requestService, times(0)).getRequestById(request.getId());


    }

    @Test(expected =Exception.class)
    public void createTransactionTest_Return() throws Exception {
        // when mocking this, mock some different issue transcation
        Book book = Book.builder()
                .id(40000)
                .name("Random book name")
                .build();


        Student student = Student.builder()
                .id(2000)
                .name("Ram")
                .build();
        Book updatedBook = Book.builder()
                .id(40000)
                .name("Random book name")
                .student(student)
                .build();

        Request returnRequest = Request.builder()
                .id(10000)
                .requestType(RequestType.RETURN)
                .my_student(student)
                .book(book)
                .build();

        Request issueRequest = Request.builder()
                .id(9999)
                .requestType(RequestType.RETURN)
                .build();


        Transcation transaction = Transcation.builder()
                .id(23)
                .externalTransactionId(UUID.randomUUID().toString())
                .transactionDate(new Date(System.currentTimeMillis()))
                .build();

        List<Transcation> transcationList = Collections.singletonList(Transcation.builder()
                .id(22)
                .transactionStatus(TransactionStatus.SUCCESSFUL)
                .request(issueRequest)
                .transactionDate(new Date(1670566705000l))
                .build());

        when(transactionRepository.findTranscationByRequest_Book_IdAndTransactionStatusOrderByTransactionDateDesc(book.getId(), TransactionStatus.SUCCESSFUL)).thenReturn(transcationList);

//        when(transactionRepository.save(any())).thenReturn(transaction);
//        when(bookService.saveOrUpdateBook(any())).thenReturn(updatedBook);
//        when(transactionRepository.save(any())).thenReturn(transaction);


        String txnId = transactionService.createTransaction(returnRequest);
//
//        verify(bookService, times(1)).saveOrUpdateBook(any());
//        verify(transactionRepository, times(2)).save(any());
//        verify(requestService, times(0)).getRequestById(returnRequest.getId());

        // when(
    }

}
