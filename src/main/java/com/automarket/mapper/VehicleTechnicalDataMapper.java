package com.automarket.mapper;

import com.automarket.dto.VehicleTechnicalDataDto;
import com.automarket.model.VehicleTechnicalData;

public class VehicleTechnicalDataMapper implements Mapper<VehicleTechnicalData, VehicleTechnicalDataDto> {

    @Override
    public VehicleTechnicalDataDto mapToDto(VehicleTechnicalData technicalData){
        VehicleTechnicalDataDto technicalDataDto = new VehicleTechnicalDataDto();
        technicalDataDto.setGearbox(technicalData.getGearbox());
        technicalDataDto.setEngine(technicalData.getEngine());
        technicalDataDto.setPowerHp(technicalData.getPowerHp());
        technicalDataDto.setEngineVolume(technicalData.getEngineVolume());
        technicalDataDto.setEmptyWeightKg(technicalData.getEmptyWeightKg());
        return technicalDataDto;
    }

    @Override
    public VehicleTechnicalData mapFromDto(VehicleTechnicalDataDto technicalDataDto){
        VehicleTechnicalData technicalData = new VehicleTechnicalData();
        technicalData.setGearbox(technicalDataDto.getGearbox());
        technicalData.setEngine(technicalDataDto.getEngine());
        technicalData.setPowerHp(technicalDataDto.getPowerHp());
        technicalData.setEngineVolume(technicalDataDto.getEngineVolume());
        technicalData.setEmptyWeightKg(technicalDataDto.getEmptyWeightKg());
        return technicalData;
    }
}
