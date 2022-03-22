package com.example.jbdl.minorproject1.requests;


import com.example.jbdl.minorproject1.models.Author;
import com.example.jbdl.minorproject1.models.Book;
import com.example.jbdl.minorproject1.models.enums.Genre;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookCreateRequest {

    @NotNull
    private String bookName;

    @NotNull
    private Genre bookGenre;

    @NotNull
    private String authorName;

    @NotNull
    @Email
    private  String authorEmail;

    private  String authorWebsite;

    public Book toBook()
    {
        return Book.builder()
                .name(this.bookName)
                .genre(this.bookGenre)
                .build();
    }

    public Author toAuthor()
    {
        return Author.builder()
                .name(this.authorName)
                .email(this.authorEmail)
                .website(this.authorWebsite)
                .build();
    }

}
