package com.example.jbdl.minorproject1.services;

import com.example.jbdl.minorproject1.models.Admin;
import com.example.jbdl.minorproject1.models.Student;
import com.example.jbdl.minorproject1.repository.StudentRepository;
import com.example.jbdl.minorproject1.requests.PlaceRequest;
import com.example.jbdl.minorproject1.requests.StudentCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    AdminService adminService;

    @Autowired
    RequestService requestService;


    public void createStudent(StudentCreateRequest studentCreateRequest){
        studentRepository.save(studentCreateRequest.toStudent());

    }

    public Student getStudentById(int id)
    {
        return studentRepository.findById(id).orElse(null);
    }


    public String placeRequest(PlaceRequest placeRequest){

        List<Admin> adminList = adminService.getAdmins(); // getting all the admins

        Admin admin = adminList.stream()
                .min(Comparator.comparingInt(a -> a.getRequestToProcess().size()))
                .orElse(null); // comparing and get the admin with least number of requests

        return requestService.saveOrUpdateRequest(placeRequest.toRequest(admin)).getExternalRequestId();

    }
}
