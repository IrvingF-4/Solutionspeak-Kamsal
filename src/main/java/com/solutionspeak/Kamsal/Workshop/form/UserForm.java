package com.solutionspeak.Kamsal.Workshop.form;

import com.solutionspeak.Kamsal.Workshop.entity.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.format.annotation.NumberFormat;

import java.io.Serializable;

@Data
public class UserForm implements Serializable {

    @Size(max = 50)
    private String username;

    @Size(max = 100)
    @NotBlank
    private String email;

    @NumberFormat(pattern = "/^\\d{10}$/\n")
    private long phone;

    @Size(max = 255)
    private String password;

    private Role role;

    public void setId(final int id){
    }
}
