package com.example.jbdl.minorproject1.models;


import com.example.jbdl.minorproject1.models.enums.Genre;
import com.example.jbdl.minorproject1.responses.BookResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book { // for author . this is the Referencing Class (child)

    @Id
    @SequenceGenerator(name="BookSequence" , sequenceName = "BookSequence" , allocationSize = 1 , initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "BookSequence")
    private int id;
    private String name;
    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    @ManyToOne
    @JoinColumn // this will store the primary key of author table here .
    @JsonIgnoreProperties("bookList")
    private Author author;


    @ManyToOne// 2 parts Many (Book) to One (Student)
    @JoinColumn
    @JsonIgnoreProperties("bookList")
    private Student student; // if the student is null. then the book is available else it is not .
    // Boolean isAvailable ; is not required as we can chec k the above condition


    @OneToMany(mappedBy = "book")
    @JsonIgnoreProperties("book")
    private List<Request> requestList ;

    public BookResponse toBookResponse()
    {
        return BookResponse.builder()
                .id(this.id)
                .name(this.name)
                .genre(this.genre)
                .author(this.author)
                .build();
    }



}
