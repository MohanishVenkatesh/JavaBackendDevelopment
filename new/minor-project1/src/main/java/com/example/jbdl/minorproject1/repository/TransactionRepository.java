package com.example.jbdl.minorproject1.repository;

import com.example.jbdl.minorproject1.models.Transcation;
import com.example.jbdl.minorproject1.models.enums.TransactionStatus;
import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transcation,Integer> {
    List<Transcation> findTranscationByRequest_Book_IdAndTransactionStatusOrderByTransactionDateDesc(int bookId , TransactionStatus transactionStatus);

}
