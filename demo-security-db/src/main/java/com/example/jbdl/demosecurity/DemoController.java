package com.example.jbdl.demosecurity;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    //This can be accessed by anyone without logging in
    @GetMapping("/hello")
    public  String sayHello(){
        return "Hello Guest!!!";
    }
    // This can be accessed  by only those people who have STUDENT authority
    @GetMapping("/student/hello")
    public String sayHelloToStudent()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUser myUser= (MyUser) authentication.getPrincipal();
        return "Hello user!!" + myUser.getUsername();
    }
    // This can be accessed by only those people who have Admin authority
    @GetMapping("/admin/hello")
    public String sayHellotoAdmin()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUser myUser = (MyUser) authentication.getPrincipal();
        return "Hello admin!!" + myUser.getUsername();
    }
    //Both student and admin should be able to access this API
    @GetMapping("/student/attendance")
    public Integer getAttendance()
    {
        return 0 ;
    }
    //Only the student should be able to access the API
    @PostMapping("/student/attendance")
    public String submitAttendance()
    {
        return null ;
    }

}
