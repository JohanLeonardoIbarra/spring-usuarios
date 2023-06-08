package com.practice.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.practice.rest.dto.UserDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity(name = "`user`")
public class User {
    @Id
    @GeneratedValue
    private Long Id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name="surname")
    private String surname;

    @Email
    @NotNull
    @Column(name = "email", unique = true)
    private String email;

    @NotNull
    @JsonIgnore
    @Column(name = "password")
    private String password;

    @JsonIgnore
    public void setData(UserDto user) {
        this.name = user.getName();
        this.surname = user.getSurname();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }
}
