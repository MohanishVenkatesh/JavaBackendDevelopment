package com.example.jbdl.minorproject1.responses;


import com.example.jbdl.minorproject1.models.Author;
import com.example.jbdl.minorproject1.models.enums.Genre;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {

    private int id;

    private String name;

    private Genre genre;

    @JsonIgnoreProperties("bookList") // we dont need to fetch the book list details.
    private Author author;
}
