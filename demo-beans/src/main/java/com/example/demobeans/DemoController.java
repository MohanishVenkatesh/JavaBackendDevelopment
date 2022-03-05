package com.example.demobeans;

import ch.qos.logback.classic.pattern.SyslogStartConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.sax.SAXResult;
import java.util.ArrayList;
import java.util.Random;
@RestController
public class DemoController {
    private static final Logger logger = LogManager.getLogger(DemoController.class);
    @Autowired
    DemoService demoService ; //

    @Autowired
    ArrayList<Integer> al;
    @GetMapping("/demo")
    public String demoFunc(@RequestParam("userInput") String userInput )
    {
        logger.info("User Input" + userInput);
        al.add(23);

//        DemoService ds = new DemoService(); // if we do this then the autowired then we can access demo object in this class .as it wouldn't be a created . and it would throw a null pointer exception.
//        System.out.println(ds.getDemo());
        System.out.println(this.demoService);
        System.out.println(demoService.getDemo().getName()); // here it wouldn't throw a NullPointerException . As demo is bean .  so it would be available in the IOC container

        return String.valueOf(new Random().nextInt());

    }

    @GetMapping("/newdemo")
    public String demoFunction()
    {
        System.out.println(this.demoService);
        return String.valueOf(new Random().nextInt());
    }
}

//same class will reference the same bean(Demo Controller)
//        api 1:
//        com.example.demobeans.DemoService@35342d2f
//        api 2:
//        com.example.demobeans.DemoService@35342d2f
//
//
//        this bean is from another class (Demo controller 2)
//com.example.demobeans.DemoService@47ad69f7
