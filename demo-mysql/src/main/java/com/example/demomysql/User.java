package com.example.demomysql;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class User {

    private int id ; // primary key
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private boolean isSeniorCitizen;

}
