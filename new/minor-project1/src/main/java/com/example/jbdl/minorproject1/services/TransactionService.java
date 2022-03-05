package com.example.jbdl.minorproject1.services;

import com.example.jbdl.minorproject1.models.Book;
import com.example.jbdl.minorproject1.models.Request;
import com.example.jbdl.minorproject1.models.Student;
import com.example.jbdl.minorproject1.models.Transcation;
import com.example.jbdl.minorproject1.models.enums.RequestType;
import com.example.jbdl.minorproject1.models.enums.TransactionStatus;
import com.example.jbdl.minorproject1.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    RequestService requestService;

    @Autowired
    BookService bookService;

    @Value("${book.allotted.max_days}")
    int allottedDays;

    @Value("${book.fine.per_day}")
    int finePerDay;

    public String createTransaction(int requestId) throws Exception {
        Request request = Request.builder().id(requestId).build();
        // when you try to pass a request with only request id . it will point to th
        // at request.
        return createTransaction(request);
    }


    public String createTransaction(Request request) throws Exception {

            Transcation transcation = Transcation.builder()
                    .externalTransactionId(UUID.randomUUID().toString())
                    .request(request)
                    .transactionStatus(TransactionStatus.PENDING)
                    .fine(calculateFine(request))
                    .build();


        try {
            Transcation savedTransaction = transactionRepository.save(transcation);
            if (request.getBook() == null || request.getMy_student() == null) { // why are we checking this ? .
                request = requestService.getRequestById(request.getId());
            }

            //Actual transaction process
            switch (request.getRequestType()) {
                case ISSUE:
                    issueBook(request);
                    break;
                case RETURN:
                    returnBook(request);
                    break;

            }
            savedTransaction.setTransactionStatus(TransactionStatus.SUCCESSFUL);
            transactionRepository.save(savedTransaction);
        }catch (Exception exception)
        {
            transcation.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transcation);

        }
        return transcation.getExternalTransactionId();



    }

    private void issueBook(Request request)
    {
        //Assigning the book to student

        Book requestedBook = request.getBook();
        Student student = request.getMy_student();
        requestedBook.setStudent(student);
        // save the book next
        bookService.saveOrUpdateBook(requestedBook);

    }
    private void returnBook(Request request)
    {
        Book requestedBook = request.getBook();
        requestedBook.setStudent(null);
        bookService.saveOrUpdateBook(requestedBook);

    }

    public Double calculateFine(Request request) throws Exception {
        if (RequestType.ISSUE.equals(request.getRequestType())) {
            return null;
        }
        List<Transcation> transcationList = transactionRepository.findTranscationByRequest_Book_IdAndTransactionStatusOrderByTransactionDateDesc(
                request.getBook().getId(), TransactionStatus.SUCCESSFUL);

        Transcation txn = transcationList.get(0);
        if (txn.getRequest().getRequestType() != RequestType.ISSUE)
        {
            throw new Exception("Last Transaction is not return ") ;
        }
            long timeOfIssueInMillis = txn.getTransactionDate().getTime();
            long timeDiff = System.currentTimeMillis() - timeOfIssueInMillis;
            // convert the timediff which is in Milliseconds to DAYS. and store it in noOfDaysPassed
            long noOfDaysPassed = TimeUnit.DAYS.convert(timeDiff,TimeUnit.MILLISECONDS);
            double fine = 0.0;
            if(noOfDaysPassed>allottedDays)
            {
                fine += (noOfDaysPassed-allottedDays) * finePerDay;

            }



        // find the book issue last time successfully
        return fine;
    }
}
