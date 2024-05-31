package com.solutionspeak.Kamsal.Workshop.form;

import com.solutionspeak.Kamsal.Workshop.entity.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Data
public class PartForm implements Serializable {

    @Size(max = 255)
    @NotBlank
    private final String name;

    @Size(max = 255)
    private final String description;

    @Size(max = 50)
    @NotBlank
    private final String category;

    private final float price;

    @NotNull
    private final Status status;

    private final int workshopId;
}