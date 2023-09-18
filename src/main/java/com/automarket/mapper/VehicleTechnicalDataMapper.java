package com.automarket.mapper;

import com.automarket.dto.VehicleTechnicalDataDto;
import com.automarket.model.VehicleTechnicalData;

public class VehicleTechnicalDataMapper {

    public VehicleTechnicalDataDto vehicleTechnicalDataParseToDto(VehicleTechnicalData technicalData){
        VehicleTechnicalDataDto technicalDataDto = new VehicleTechnicalDataDto();
        technicalDataDto.setGearbox(technicalData.getGearbox());
        technicalDataDto.setEngine(technicalData.getEngine());
        technicalDataDto.setPowerHp(technicalData.getPowerHp());
        technicalDataDto.setEngineVolume(technicalData.getEngineVolume());
        technicalDataDto.setEmptyWeightKg(technicalData.getEmptyWeightKg());
        return technicalDataDto;
    }

    public VehicleTechnicalData vehicleTechnicalDataParseFromDto(VehicleTechnicalDataDto technicalDataDto){
        VehicleTechnicalData technicalData = new VehicleTechnicalData();
        technicalData.setGearbox(technicalDataDto.getGearbox());
        technicalData.setEngine(technicalDataDto.getEngine());
        technicalData.setPowerHp(technicalDataDto.getPowerHp());
        technicalData.setEngineVolume(technicalDataDto.getEngineVolume());
        technicalData.setEmptyWeightKg(technicalDataDto.getEmptyWeightKg());
        return technicalData;
    }
}
