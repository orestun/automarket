package com.automarket.dto;

import com.automarket.model.Role;
import com.automarket.model.Vehicle;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class UserDto {
    private String firthName;
    private String secondName;
    private String username;
    private String email;
    private String phoneNumber;
    private String password;
    private String photoURL;
    private Set<Vehicle> vehiclesForSale;
    private List<Role> roles;
}
