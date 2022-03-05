package com.example.demobeans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
@RestController
public class DemoController2 {
    @Autowired
    DemoService demoService ; //

    @Autowired
    ArrayList<Integer> bl;

    @Autowired
    DemoConfig demoConfig;


    @GetMapping("/demo2")
    public String demoFunc()
    {

        List<Integer> list = demoConfig.getArraylist();
        list.add(10);
       System.out.println(list);
//        System.out.println(bl);
//        System.out.println(this.demoService);
//        System.out.println(demoService.getDemo().getName());

        return String.valueOf(new Random().nextInt());

    }
}
