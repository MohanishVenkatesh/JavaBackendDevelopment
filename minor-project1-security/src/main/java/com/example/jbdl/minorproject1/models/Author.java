package com.example.jbdl.minorproject1.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Author { // this is the Refernced class (Parent)
    @Id
    @SequenceGenerator(name="AuthorSequence" , sequenceName = "AuthorSequence" , allocationSize = 1 , initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "AuthorSequence")
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private  String email;

    private  String website;

    @OneToMany(mappedBy = "author")
    @JsonIgnoreProperties({"author","requestList","student"})
    private List<Book> bookList = new java.util.ArrayList<>();
    // bookList attribute is mapped to author in the Book table ;

}
