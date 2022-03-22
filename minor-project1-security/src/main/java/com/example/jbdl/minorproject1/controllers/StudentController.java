package com.example.jbdl.minorproject1.controllers;

import com.example.jbdl.minorproject1.models.Student;
import com.example.jbdl.minorproject1.requests.PlaceRequest;
import com.example.jbdl.minorproject1.requests.StudentCreateRequest;
import com.example.jbdl.minorproject1.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/student")
    public void createStudent(@Valid @RequestBody  StudentCreateRequest studentCreateRequest)
    {
        studentService.createStudent(studentCreateRequest);

    }

    @GetMapping("/student/{studentId}")
    public Student getStudent(@Valid @PathVariable("studentId") int studentId)
    {
        return studentService.getStudentById(studentId);

    }

    @PostMapping("/student/request")
    public String placeRequest(@Valid @RequestBody PlaceRequest placeRequest)
    {
        return  studentService.placeRequest(placeRequest);
    }
}
