package com.automarket.mapper;

import com.automarket.dto.VehicleBasicInfoDto;
import com.automarket.model.VehicleBasicInfo;

public class VehicleBasicInfoMapper implements Mapper<VehicleBasicInfo, VehicleBasicInfoDto>{

    @Override
    public VehicleBasicInfoDto mapToDto(VehicleBasicInfo basicInfo){
        VehicleBasicInfoDto basicInfoDto = new VehicleBasicInfoDto();
        basicInfoDto.setInteriorColor(basicInfo.getInteriorColor());
        basicInfoDto.setExteriorColor(basicInfo.getExteriorColor());
        basicInfoDto.setType(basicInfo.getType());
        basicInfoDto.setDoors(basicInfo.getDoors());
        basicInfoDto.setDescription(basicInfo.getDescription());
        return basicInfoDto;
    }

    @Override
    public VehicleBasicInfo mapFromDto(VehicleBasicInfoDto basicInfoDto){
        VehicleBasicInfo basicInfo = new VehicleBasicInfo();
        basicInfo.setInteriorColor(basicInfoDto.getInteriorColor());
        basicInfo.setExteriorColor(basicInfoDto.getExteriorColor());
        basicInfo.setType(basicInfoDto.getType());
        basicInfo.setDoors(basicInfoDto.getDoors());
        basicInfo.setDescription(basicInfoDto.getDescription());
        return basicInfo;
    }
}
