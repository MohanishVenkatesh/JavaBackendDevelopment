package com.example.jbdl.minorproject1.services;

import com.example.jbdl.minorproject1.models.Admin;
import com.example.jbdl.minorproject1.models.Request;
import com.example.jbdl.minorproject1.repository.RequestRepository;
import com.example.jbdl.minorproject1.requests.PlaceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class RequestService {
    @Autowired
    RequestRepository requestRepository;




     public Request getRequestById(Integer id)
     {
         return requestRepository.findById(id).orElse(null);

     }

     public Request saveOrUpdateRequest(Request request)
     {
          return  requestRepository.save(request);
     }
}
