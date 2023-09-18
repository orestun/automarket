package com.automarket.dto;

import com.automarket.utils.Colour;
import com.automarket.utils.VehicleType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleBasicInfoDto {
    private Colour exteriorColor;
    private Colour interiorColor;
    private int doors;
    private VehicleType type;
    private String description;
}
