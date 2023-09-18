package com.automarket.mapper;

import com.automarket.dto.VehicleDto;
import com.automarket.model.Vehicle;

public class VehicleMapper {
    private final VehicleHistoryMapper historyMapper = new VehicleHistoryMapper();
    private final VehicleBasicInfoMapper basicInfoMapper = new VehicleBasicInfoMapper();
    private final VehicleOrderInfoMapper orderInfoMapper = new VehicleOrderInfoMapper();
    private final VehicleTechnicalDataMapper technicalDataMapper = new VehicleTechnicalDataMapper();
    private final UserMapper userMapper = new UserMapper();

    public VehicleDto vehicleParseToDto(Vehicle vehicle){
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setMake(vehicle.getMake());
        vehicleDto.setModel(vehicle.getModel());
        vehicleDto.setVINCode(vehicle.getVINCode());
        vehicleDto.setConfiguration(vehicle.getConfiguration());
        vehicleDto.setCreateTimestamp(vehicle.getCreateTimestamp());
        vehicleDto.setUpdateTimeStamp(vehicle.getUpdateTimeStamp());
        vehicleDto
                .setSeller(userMapper.UserParseToDto(vehicle.getSeller()));
        vehicleDto
                .setHistory(historyMapper.historyParseToDto(vehicle.getHistory()));
        vehicleDto
                .setBasicInfo(basicInfoMapper.basicInfoParseToDto(vehicle.getBasicInfo()));
        vehicleDto
                .setOrderInfo(orderInfoMapper.orderInfoParseToDto(vehicle.getOrderInfo()));
        vehicleDto
                .setTechnicalData(technicalDataMapper.vehicleTechnicalDataParseToDto(vehicle.getTechnicalData()));
        return vehicleDto;
    }

    public Vehicle vehicleParseFromDto(VehicleDto vehicleDto){
        Vehicle vehicle = new Vehicle();
        vehicle.setMake(vehicleDto.getMake());
        vehicle.setModel(vehicleDto.getModel());
        vehicle.setVINCode(vehicleDto.getVINCode());
        vehicle.setConfiguration(vehicleDto.getConfiguration());
        vehicle
                .setSeller(userMapper.UserParseFromDto(vehicleDto.getSeller()));
        vehicle
                .setHistory(historyMapper.historyParseFromDto(vehicleDto.getHistory()));
        vehicle
                .setBasicInfo(basicInfoMapper.basicInfoParseFromDto(vehicleDto.getBasicInfo()));
        vehicle
                .setOrderInfo(orderInfoMapper.orderInfoParseFromDto(vehicleDto.getOrderInfo()));
        vehicle
                .setTechnicalData(technicalDataMapper.vehicleTechnicalDataParseFromDto(vehicleDto.getTechnicalData()));
        return vehicle;
    }
}
