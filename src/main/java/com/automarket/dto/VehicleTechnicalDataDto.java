package com.automarket.dto;

import com.automarket.utils.EngineType;
import com.automarket.utils.GearboxType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VehicleTechnicalDataDto {
    private GearboxType gearbox;
    private EngineType engine;
    private int engineVolume;
    private int powerHp;
    private int emptyWeightKg;
}
