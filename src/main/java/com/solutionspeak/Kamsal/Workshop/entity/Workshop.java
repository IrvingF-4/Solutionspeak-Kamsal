package com.solutionspeak.Kamsal.Workshop.entity;

import com.solutionspeak.Kamsal.Workshop.form.WorkshopForm;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "workshops")
@NoArgsConstructor
public class Workshop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "contact")
    private String contact;

    @Column(name = "email")
    private String email;

    @Column(name = "user_id")
    private int userId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
    private User user;

    public Workshop(final WorkshopForm form) {
        this.name = form.getName();
        this.address = form.getAddress();
        this.contact = form.getContact();
        this.email = form.getEmail();
        this.userId = form.getUserId();
    }

    public void updateWorkshop(final WorkshopForm form) {
        this.name = form.getName();
        this.address = form.getAddress();
        this.contact = form.getContact();
        this.email = form.getEmail();
        this.userId = form.getUserId();
    }
}
