package com.solutionspeak.Kamsal.Workshop.dto;

import com.solutionspeak.Kamsal.Workshop.entity.Workshop;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
public class WorkshopDTO implements Serializable {

    private final int id;

    private final String name;

    private final String address;

    private final String contact;

    private final String email;

    private final int userId;

    private final UserDTO user;

    public static WorkshopDTO build(final Workshop workshop) {
        return WorkshopDTO.builder()
                .id(workshop.getId())
                .name(workshop.getName())
                .address(workshop.getAddress())
                .contact(workshop.getContact())
                .userId(workshop.getUserId())
                .build();
    }

    public static WorkshopDTO build(final Workshop workshop, final UserDTO user) {
        return WorkshopDTO.builder()
                .id(workshop.getId())
                .name(workshop.getName())
                .address(workshop.getAddress())
                .contact(workshop.getContact())
                .userId(workshop.getUserId())
                .user(user)
                .build();
    }
}