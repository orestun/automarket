package com.automarket.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Nullable
    @Column(name = "firth_name", length = 35)
    private String firthName;

    @Nullable
    @Column(name = "second_name", length = 35)
    private String secondName;

    @NotNull(message = "Username can`t be null")
    @Column(name = "username", length = 50, unique = true)
    private String username;

    @NotNull(message = "Email can`t be null")
    @Email(message = "Wrong email syntax")
    @Column(name = "email", unique = true)
    private String email;

    @Nullable
    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @Column(name = "password")
    private String password;

    @Nullable
    private String photoURL;

    @Nullable
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Vehicle> vehiclesForSale;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "role", joinColumns = @JoinColumn(name = "user_id"))
    private List<Role> roles;
}
