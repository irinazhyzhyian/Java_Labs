package com.lab3.lab3.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "users")
public class Users {
    public Users() {

    }

    public enum UserRole {
        LIBRARIAN,
        READER;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "role_id")
    private UserRole role;


    public Users(String firstName, String lastName, String email, String password, UserRole role) {
        this.name = firstName;
        this.secondName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

}
