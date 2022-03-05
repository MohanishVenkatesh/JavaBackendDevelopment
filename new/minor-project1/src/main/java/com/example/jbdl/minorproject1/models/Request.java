package com.example.jbdl.minorproject1.models;

import com.example.jbdl.minorproject1.models.enums.RequestStatus;
import com.example.jbdl.minorproject1.models.enums.RequestType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {
    @Id
    @SequenceGenerator(name="RequestSequence" , sequenceName = "RequestSequence" , allocationSize = 1 , initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "RequestSequence")
    private int id;

    private String externalRequestId;

    @JoinColumn
    @ManyToOne
    @JsonIgnoreProperties("requestCreated")
    private Student my_student;

    @Enumerated(value = EnumType.STRING)
    private RequestType requestType;
    @Enumerated(value = EnumType.STRING)
    private RequestStatus requestStatus;

        @JoinColumn
        @ManyToOne
        @JsonIgnoreProperties("requestToProcess")
        private Admin admin;

    @OneToOne(mappedBy = "request")
    @JsonIgnoreProperties("request")
    private Transcation transcation;

    @JoinColumn
    @ManyToOne
    @JsonIgnoreProperties("requestList")
    private Book book ;

    @CreationTimestamp
    private Date  requestDate;

    private String adminComment;

    private String systemComment;



}
