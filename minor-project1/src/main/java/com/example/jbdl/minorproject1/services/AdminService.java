package com.example.jbdl.minorproject1.services;

import com.example.jbdl.minorproject1.models.Admin;
import com.example.jbdl.minorproject1.models.Request;
import com.example.jbdl.minorproject1.models.enums.RequestStatus;
import com.example.jbdl.minorproject1.repository.AdminRepository;
import com.example.jbdl.minorproject1.requests.AdminCreateRequest;
import com.example.jbdl.minorproject1.requests.ProcessRequest;
import com.example.jbdl.minorproject1.responses.ProcessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    private static final String REJECTED_STATUS ="Rejected";
    private static final String APPROVED_STATUS ="Approved";

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    RequestService requestService;

    @Autowired
    TransactionService transactionService;

    public void createAdmin(AdminCreateRequest adminCreateRequest) {
        adminRepository.save(adminCreateRequest.toAdmin());


    }

    public Admin getAdmin(Integer adminId) {
        return adminRepository.findById(adminId).orElse(null);
    }

    public List<Admin> getAdmins() {
        return adminRepository.findAll();
    }

    public ProcessResponse processRequest(ProcessRequest processRequest) throws Exception {

        Request request = requestService.getRequestById(processRequest.getRequestId());
        if (request == null) {
            throw new Exception("Request does not exist");
        }

        if (request.getAdmin() == null && request.getAdmin().getId() != processRequest.getRequestId()) {
            throw new Exception("Request is not assigned to the this admin " + processRequest.getAdminId());
        }

        if (!RequestStatus.PENDING.equals(request.getRequestStatus())) // this is to check whether in DB it already
        // accepted or rejected.
        {
            throw new Exception("Request is already processed");
        }
         // L1 : Admin approval, L2 : System approval
        if (RequestStatus.REJECTED.equals(processRequest.getRequestStatus())) {
            request.setRequestStatus(RequestStatus.REJECTED);
            request.setAdminComment(processRequest.getComment());
            request.setSystemComment(REJECTED_STATUS);
            requestService.saveOrUpdateRequest(request);
            return ProcessResponse.builder()
                    .systemComment(REJECTED_STATUS)
                    .requestStatus(RequestStatus.REJECTED)
                    .adminComment(processRequest.getComment())
                    .build();
        }

        // if the program reaches the switch statement then we can say that admin accepted the request .

        switch (request.getRequestType()) {
            case ISSUE:
                //TODO 1:  Book exists and not issued, 2. check for students threshold . 3. Approve the status and create a Transcation.

                break;

            case RETURN:
                request.setRequestStatus(RequestStatus.ACCEPTED);
                request.setAdminComment(processRequest.getComment());
                request.setSystemComment(APPROVED_STATUS);
               // transactionService.createTransaction();
                return ProcessResponse.builder()
                        .systemComment(APPROVED_STATUS)
                        .requestStatus(RequestStatus.ACCEPTED)
                        .adminComment(processRequest.getComment())
                        .build();


        }

        return null;

    }
}
