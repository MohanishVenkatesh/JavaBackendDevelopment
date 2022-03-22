package com.example.jbdl.minorproject1.repository;

import com.example.jbdl.minorproject1.models.Book;
import com.example.jbdl.minorproject1.models.enums.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {

    //1.JPQL  - Java Persistence Query language
    //2.Native Sql QUERY  - Queries with sql tables

    List<Book> findBookByGenreEndingWith(String endStr);
    //-> select * from book where genrel iek '%endStr';


    @Query("select b from Book b where b.genre =:genre")
    List<Book> getBookByGenre(Genre genre);

    @Query(value = "select * from book b where b.genre = ?1 ",nativeQuery = true)
    List<Book> getBookByGenreSql(String genre);

    //select * from book b join author a on b.author_id = a.id where a.email = ?1"
    List<Book> findAllByAuthor_Email(String email);

    //select * from book b where b.student is not  null ;
    List<Book> findAllByStudentIsNotNull();

    //select * from book b where b.student is null;
    List<Book> findAllByStudentIsNull();
}
