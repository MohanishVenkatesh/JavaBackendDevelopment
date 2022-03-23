package com.example.jbdl.minorproject1.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Admin {
    @Id
    @SequenceGenerator(name = "AdminSequence", sequenceName = "AdminSequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AdminSequence")
    private int id;

    @Column(unique = true, nullable = false)
    private  String name;
    @Column(unique = true, nullable = false)
    private String email;


    @OneToMany(mappedBy = "admin")
    @JsonIgnoreProperties({"admin","my_student","transcation","book"}) // while printing admin don't print the admin inside the request .
    private List<Request>  requestToProcess;

    @CreationTimestamp
    private Date createOn;
}
