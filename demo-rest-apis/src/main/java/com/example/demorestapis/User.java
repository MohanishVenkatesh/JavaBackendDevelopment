package com.example.demorestapis;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class User {
    private Integer id;
    private String name;
    private Integer age;

}