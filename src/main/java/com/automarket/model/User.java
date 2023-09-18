package com.automarket.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User {

    @Id
    private Long id;

    @Column(name = "firth_name", length = 35)
    private String firthName;

    @Column(name = "second_name", length = 35)
    private String secondName;

    @NotNull(message = "Username can`t be null")
    @Column(name = "username", length = 50, unique = true)
    private String username;

    @NotNull(message = "Email can`t be null")
    @Email(message = "Wrong email syntax")
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @Column(name = "password")
    private String password;

    private String photoURL;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Vehicle> vehiclesForSale;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "role", joinColumns = @JoinColumn(name = "user_id"))
    private List<Role> roles;
}
