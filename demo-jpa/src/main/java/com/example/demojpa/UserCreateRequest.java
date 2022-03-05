package com.example.demojpa;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.*;
import java.sql.SQLException;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateRequest {
    @Min(value = 1)
    @Max(value = 100)


    @NotNull
    private int age;

    @Size(max = 10)
    private String lastName;

    @Size(min = 1, max =20)
    private String firstName;
    @Email
    private String email;

    private Boolean isSeniorCitizen;

    public  User toUser()
    {
        return User.builder().
                lastName(lastName).
                firstName(firstName).
                email(email).
                age(age).
                isSeniorCitizen(isSeniorCitizen == null ? checkIfSeniorCitizen(): isSeniorCitizen)
                .build();
    }

    private boolean checkIfSeniorCitizen() {
        return this.age >= 60 ;
    }
}
