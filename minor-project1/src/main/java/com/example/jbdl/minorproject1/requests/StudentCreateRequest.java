package com.example.jbdl.minorproject1.requests;


import com.example.jbdl.minorproject1.models.Student;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentCreateRequest {

    @NotNull
    private String name;

    @Min(1)
    private int age;

    @NotNull
    private String rollno;

    public Student toStudent()
    {
        return Student.builder()
                .rollNo(this.rollno)
                .name(this.name)
                .age(this.age)
                .build();
    }
}
