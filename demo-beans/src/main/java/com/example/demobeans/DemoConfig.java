package com.example.demobeans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;

@Configuration
@Scope("prototype") // giving annotations here for configuration classes is irrelevant
public class DemoConfig {

    @Bean
    @Primary // when the return type is same . it will the functions with the Primary annotation .
    @Scope("singleton")
    ArrayList<Integer> getArraylist()
    {
        return new ArrayList<>();
    }

    @Bean
    @Scope("singleton")
    ArrayList<Integer> getArraylist2()
    {
        return new ArrayList<>();
    }

}
