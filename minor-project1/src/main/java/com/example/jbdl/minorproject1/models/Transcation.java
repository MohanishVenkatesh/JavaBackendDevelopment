package com.example.jbdl.minorproject1.models;

import com.example.jbdl.minorproject1.models.enums.TransactionStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transcation {
    @Id
    @SequenceGenerator(name="TransactionSequence" , sequenceName = "TransactionSequence" , allocationSize = 1 , initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "TransactionSequence")
    private int id;

    private String externalTransactionId; // Only for the outside world. So that they cant know how many
    // transcations are taking place

    @Enumerated(value = EnumType.STRING)
    private TransactionStatus transactionStatus;


//    @ManyToOne
//    @JoinColumn
//    private Admin admin;
//
//    @ManyToOne
//    @JoinColumn
//    private Book book;
//
//    @ManyToOne
//    @JoinColumn
//    private Student student;

    @OneToOne
    @JoinColumn
    @JsonIgnoreProperties("transcation")
    private Request request;

    @CreationTimestamp
    Date transactionDate;

    private Double fine; // for issue with have as NULL ;


}
// IssueBook (Transaction )
// Return (Transaction)