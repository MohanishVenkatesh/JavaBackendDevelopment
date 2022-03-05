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
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    @Id
    @SequenceGenerator(name="StudentSequence" , sequenceName = "StudentSequence" , allocationSize = 1 , initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "StudentSequence")
    private int id;

    @Column(nullable = false)
    private String name;

    private int age;

    @Column(unique = true , nullable = false)
    private String rollNo;

    @OneToMany(mappedBy = "student")
    @JsonIgnoreProperties("student")
    private List<Book> bookList ;


    @OneToMany  (mappedBy = "my_student")
    @JsonIgnoreProperties("my_student")
    private List<Request>  requestCreated;

    @CreationTimestamp
    private Date createOn;

}
