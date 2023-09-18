package com.automarket.dto;

import com.automarket.utils.Colour;
import com.automarket.utils.EngineType;
import com.automarket.utils.GearboxType;
import com.automarket.utils.VehicleType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleSearchCriteriaDto {
    private String make;
    private String model;
    private String configuration;
    private String country;
    private String state;
    private String city;
    private Colour exteriorColor;
    private Colour interiorColor;
    private VehicleType type;
    private GearboxType gearbox;
    private EngineType engine;
    private int minMileage;
    private int maxMileage;
    private int minYear;
    private int maxYear;
    private int minPrice;
    private int maxPrice;
    private Boolean wasInAccident;
    private Boolean wasMoved;
    private Boolean isTradeInIncluded;
    private Boolean isAuctionIncluded;
}
