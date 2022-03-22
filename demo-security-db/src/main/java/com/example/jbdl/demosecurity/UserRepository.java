package com.example.jbdl.demosecurity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<MyUser,Integer>
{
    @Query("select u from MyUser u where u.userName = :s")
    MyUser findByUserName(String s);
}
