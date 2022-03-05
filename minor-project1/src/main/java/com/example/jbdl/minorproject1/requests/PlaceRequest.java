package com.example.jbdl.minorproject1.requests;

import com.example.jbdl.minorproject1.models.Admin;
import com.example.jbdl.minorproject1.models.Book;
import com.example.jbdl.minorproject1.models.Request;
import com.example.jbdl.minorproject1.models.Student;
import com.example.jbdl.minorproject1.models.enums.RequestStatus;
import com.example.jbdl.minorproject1.models.enums.RequestType;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaceRequest {

    @NotNull
    private Integer studentId;

    @NotNull
    private Integer bookId;

    @NotNull
    private String requestType;

    public Request toRequest()
    {
        return Request.builder()
                .book(Book.builder().id(this.bookId).build())
                .my_student(Student.builder().id(this.studentId).build())
                .requestType(RequestType.valueOf(this.requestType)) // converts from the string to  a enum as that enum is only accepted
                .requestStatus(RequestStatus.PENDING)
                .externalRequestId(UUID.randomUUID().toString())
                .build();

    }
    public Request toRequest(Admin admin)
    {
        return Request.builder()
                .admin(admin)
                .book(Book.builder().id(this.bookId).build())
                .my_student(Student.builder().id(this.studentId).build())
                .requestType(RequestType.valueOf(this.requestType)) // converts from the string to  a enum as that enum is only accepted
                .requestStatus(RequestStatus.PENDING)
                .externalRequestId(UUID.randomUUID().toString())
                .build();



    }

}
