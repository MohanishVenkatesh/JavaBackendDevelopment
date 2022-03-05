package com.example.demobeans;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Getter
@Setter
public class Demo {
    private int id ;
    private String name;
    private Date date;
}
