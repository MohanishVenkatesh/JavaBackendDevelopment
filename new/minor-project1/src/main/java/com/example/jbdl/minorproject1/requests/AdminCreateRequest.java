package com.example.jbdl.minorproject1.requests;


import com.example.jbdl.minorproject1.models.Admin;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminCreateRequest {

    @NotNull
    private String name;
    @NotNull
    @Email
    private String email;

    public Admin toAdmin()
    {
        return Admin.builder()
                .name(this.getName())
                .email(this.getEmail())
                .build();
    }
}
