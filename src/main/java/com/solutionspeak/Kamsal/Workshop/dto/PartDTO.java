package com.solutionspeak.Kamsal.Workshop.dto;

import com.solutionspeak.Kamsal.Workshop.entity.Part;
import com.solutionspeak.Kamsal.Workshop.entity.Status;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
public class PartDTO implements Serializable {

    private int id;

    private String name;

    private String description;

    private String category;

    private float price;

    private Status status;

    private int workshopId;

    private WorkshopDTO workshopDTO;

    public static PartDTO build(final Part part) {
        return PartDTO.builder()
                .id(part.getId())
                .name(part.getName())
                .description(part.getDescription())
                .category(part.getCategory())
                .price(part.getPrice())
                .status(part.getStatus())
                .workshopId(part.getWorkshopId())
                .build();
    }

    public static PartDTO build(final Part part, final WorkshopDTO workshopDTO) {
        return PartDTO.builder()
                .id(part.getId())
                .name(part.getName())
                .description(part.getDescription())
                .category(part.getCategory())
                .price(part.getPrice())
                .status(part.getStatus())
                .workshopId(part.getWorkshopId())
                .workshopDTO(workshopDTO)
                .build();
    }
}