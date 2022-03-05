package com.example.demobeans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class DemoService {
//    if you have a parameterized constructor then you will have to
//    pass bean(s) in the constructor arguments
    // if you have paratemeterized and default constructor then the default constructor is called .
    @Autowired // but if autowired annotation is used it tells spring which constructor to run
    public DemoService(Obja A , Objb B , @Value("${my_app.test_prop}") String prop  ){
        // in the above line we are injecting a property
        System.out.println("Creating an instance of demoService.." + A + " "  + B + " " + prop);
    }
    public DemoService()
    {
        System.out.println("in the default constructor");
    }
    @Autowired
    Demo demo;
    public Demo getDemo()
    {
        System.out.println(demo.getName());
        return this.demo;
    }
}
