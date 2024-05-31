package com.solutionspeak.Kamsal.Workshop.form;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.io.Serializable;

@Data
public class WorkshopForm implements Serializable {

    @Size(max = 255)
    @NotBlank
    private final String name;

    @Size(max = 255)
    @NotBlank
    private final String address;

    @Size(max = 100)
    @NotBlank
    private final String contact;

    @Size(max = 150)
    @Email
    private final String email;

    @Positive
    private final int userId;

}