package com.automarket.model;

import com.automarket.utils.Colour;
import com.automarket.utils.VehicleType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "vehicle_basic_info")
@Getter
@Setter
public class VehicleBasicInfo {
    @Id
    private Long id;
    private Colour exteriorColor;
    private Colour interiorColor;
    private int doors;
    private VehicleType type;
    @Column(length = 1000)
    private String description;
}
