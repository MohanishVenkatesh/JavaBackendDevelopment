package com.example.jbdl.minorproject1.services;

import com.example.jbdl.minorproject1.models.Student;
import com.example.jbdl.minorproject1.repository.StudentRepository;
import com.example.jbdl.minorproject1.requests.PlaceRequest;
import com.example.jbdl.minorproject1.requests.StudentCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    RequestService requestService;

    public void createStudent(StudentCreateRequest studentCreateRequest)
    {
        studentRepository.save(studentCreateRequest.toStudent());

    }
    public Student getStudentById(int id)
    {
        return studentRepository.findById(id).orElse(null);
    }

    public String placeRequest(PlaceRequest placeRequest) {
       return requestService.placeRequest(placeRequest) ;

    }
}
