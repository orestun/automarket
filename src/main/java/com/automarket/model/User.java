package com.automarket.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User {

    @Id
    private Long id;
    private String firthName;
    private String secondName;
    private String username;
    private String email;
    private String phoneNumber;
    private String password;
    private String photoURL;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Vehicle> vehiclesForSale;
}
