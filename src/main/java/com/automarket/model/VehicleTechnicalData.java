package com.automarket.model;

import com.automarket.utils.EngineType;
import com.automarket.utils.GearboxType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "vehicle_technical_data")
@Getter
@Setter
public class VehicleTechnicalData {

    @Id
    private Long id;
    private GearboxType gearbox;
    private EngineType engine;
    private int engineVolume;
    private int powerHp;
    private int emptyWeightKg;
}
