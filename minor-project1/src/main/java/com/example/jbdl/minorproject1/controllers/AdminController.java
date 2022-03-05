package com.example.jbdl.minorproject1.controllers;


import com.example.jbdl.minorproject1.models.Admin;
import com.example.jbdl.minorproject1.requests.AdminCreateRequest;
import com.example.jbdl.minorproject1.requests.ProcessRequest;
import com.example.jbdl.minorproject1.services.AdminService;
import oracle.ucp.proxy.annotation.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping("/admin")
    public  void createAdmin(@Valid @RequestBody AdminCreateRequest adminCreateRequest)
    {
        adminService.createAdmin(adminCreateRequest);
    }

    @GetMapping("/admin/{adminId}")
    public Admin getAdmin(@PathVariable("adminId") int adminId)
    {

        return adminService.getAdmin(adminId);
    }

    @PostMapping("/admin/process")
    public void processRequest(@Valid @RequestBody ProcessRequest processRequest) throws Exception {
        adminService.processRequest(processRequest);
    }
}
