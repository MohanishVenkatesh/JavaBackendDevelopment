package com.example.jbdl.minorproject1.responses;

import com.example.jbdl.minorproject1.models.Request;
import com.example.jbdl.minorproject1.models.enums.RequestStatus;
import lombok.*;



@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
/*
      after the admin processing the processrequest
      the processResponse will be returned.
 */
public class ProcessResponse {


    private RequestStatus requestStatus;

    private String systemComment;

    private String adminComment;

}
