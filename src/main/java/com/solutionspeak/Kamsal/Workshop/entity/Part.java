package com.solutionspeak.Kamsal.Workshop.entity;

import com.solutionspeak.Kamsal.Workshop.convertors.StatusConvertor;
import com.solutionspeak.Kamsal.Workshop.form.PartForm;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "parts")
@NoArgsConstructor
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "category")
    private String category;

    @Column(name = "price")
    private float price;

    @Column(name = "part_status")
    @Convert(converter = StatusConvertor.class)
    private Status status;

    @Column(name = "workshop_id")
    private int workshopId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workshop_id", nullable = false, insertable = false, updatable = false)
    private Workshop workshop;

    public Part(final PartForm form) {
        this.name = form.getName();
        this.description = form.getDescription();
        this.category = form.getCategory();
        this.price = form.getPrice();
        this.status = form.getStatus();
        this.workshopId = form.getWorkshopId();
    }

    public void updatePart(final PartForm form) {
        this.name = form.getName();
        this.description = form.getDescription();
        this.category = form.getCategory();
        this.price = form.getPrice();
        this.status = form.getStatus();
        this.workshopId = form.getWorkshopId();
    }
}
