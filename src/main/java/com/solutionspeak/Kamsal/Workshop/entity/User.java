package com.solutionspeak.Kamsal.Workshop.entity;

import com.solutionspeak.Kamsal.Workshop.convertors.RoleConvertor;
import com.solutionspeak.Kamsal.Workshop.form.UserForm;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone", nullable = false)
    private long phone;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    @Convert(converter = RoleConvertor.class)
    private Role role;

    public User(final UserForm form) {
        this.username = form.getUsername();
        this.email = form.getEmail();
        this.phone = form.getPhone();
        this.password = form.getPassword();
        this.role = form.getRole();
    }

    public void updateUser(final UserForm form) {
        this.username = form.getUsername();
        this.email = form.getEmail();
        this.phone = form.getPhone();
        this.password = form.getPassword();
        this.role = form.getRole();
    }

}