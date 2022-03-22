package com.example.jbdl.minorproject1.repository;

import com.example.jbdl.minorproject1.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorRepository extends JpaRepository<Author,Integer> {

    @Query("select a from Author a where a.email = :email") // select * from author where email = "abc@google.com"
    Author findByEmailId(String email);

}
