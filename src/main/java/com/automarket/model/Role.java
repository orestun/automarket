package com.automarket.model;

import com.automarket.utils.Roles;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class Role{
    private String role;
    public Role(Roles role) {
        this.role = role.name();
    }
}
