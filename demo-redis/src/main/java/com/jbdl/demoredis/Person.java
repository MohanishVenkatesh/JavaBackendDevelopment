package com.jbdl.demoredis;


import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Person implements Serializable {

    @NotNull
    private Long id;
    private String name;
    private Double height;
    private Integer weight;
    private Boolean seniorCitizen;
}
