package com.solutionspeak.Kamsal.Workshop.dto;

import com.solutionspeak.Kamsal.Workshop.entity.Workshop;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
public class WorkshopDTO implements Serializable {

    private int id;

    private String name;

    private String address;

    private String contact;

    private String email;

    private int userId;

    private UserDTO user;

    public static WorkshopDTO build(final Workshop workshop) {
        return WorkshopDTO.builder()
                .id(workshop.getId())
                .name(workshop.getName())
                .address(workshop.getAddress())
                .contact(workshop.getContact())
                .email(workshop.getEmail())
                .userId(workshop.getUserId())
                .build();
    }

    public static WorkshopDTO build(final Workshop workshop, final UserDTO user) {
        return WorkshopDTO.builder()
                .id(workshop.getId())
                .name(workshop.getName())
                .address(workshop.getAddress())
                .contact(workshop.getContact())
                .email(workshop.getEmail())
                .userId(workshop.getUserId())
                .user(user)
                .build();
    }
}