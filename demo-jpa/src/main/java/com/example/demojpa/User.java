package com.example.demojpa;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Entity (name = "UserTable")// used to tell hibernate to map this object to a table in database
public class User {

//    IDENTITY - The auto incremented values are genearted by the database
//    AUTO - The auto incremented values are generated by hibernate
//            Person, User , Transaction - 1 , 2 , 3 - AUTO (THIS IS ON A GLOBAL LEVEL)
//            Person, User, Transcation - 1, 1 , 1 - IDENTITY (ALL TABLES WITH START WITH 1)

    @Id //primary_key
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "SEQUENCE1")
    @SequenceGenerator(name="SEQUENCE1", sequenceName="SEQUENCE1", allocationSize=1 , initialValue = 1) // for oracle this is the way to generate autoincrement
    private int id ; // primary key

    private String firstName;
    private String lastName;
    private int age;

    @Column(unique = true , nullable = false , length = 30)
    private String email;

    @Column(name = "senior_citizen")
    private boolean isSeniorCitizen;
}