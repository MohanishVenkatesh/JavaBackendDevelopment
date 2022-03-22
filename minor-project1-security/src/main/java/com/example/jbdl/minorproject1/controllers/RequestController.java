package com.example.jbdl.minorproject1.controllers;

import com.example.jbdl.minorproject1.requests.PlaceRequest;
import com.example.jbdl.minorproject1.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class RequestController {
    @Autowired
    RequestService requestService;


}
