package com.solutionspeak.Kamsal.Workshop.dto;

import com.solutionspeak.Kamsal.Workshop.entity.Role;
import com.solutionspeak.Kamsal.Workshop.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
public class UserDTO implements Serializable {

    private int id;

    private String username;

    private String email;

    private long phone;

    private String password;

    private Role role;

    public static UserDTO build(final User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .phone(user.getPhone())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }

}