package com.automarket.mapper;

import com.automarket.dto.VehicleDto;
import com.automarket.model.Vehicle;

public class VehicleMapper implements Mapper<Vehicle, VehicleDto>{
    private final VehicleHistoryMapper historyMapper = new VehicleHistoryMapper();
    private final VehicleBasicInfoMapper basicInfoMapper = new VehicleBasicInfoMapper();
    private final VehicleOrderInfoMapper orderInfoMapper = new VehicleOrderInfoMapper();
    private final VehicleTechnicalDataMapper technicalDataMapper = new VehicleTechnicalDataMapper();
    private final UserMapper userMapper = new UserMapper();

    @Override
    public VehicleDto mapToDto(Vehicle vehicle){
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setMake(vehicle.getMake());
        vehicleDto.setModel(vehicle.getModel());
        vehicleDto.setVINCode(vehicle.getVINCode());
        vehicleDto.setConfiguration(vehicle.getConfiguration());
        vehicleDto.setCreateTimestamp(vehicle.getCreateTimestamp());
        vehicleDto.setUpdateTimeStamp(vehicle.getUpdateTimeStamp());
        vehicleDto
                .setSeller(userMapper.mapToDto(vehicle.getSeller()));
        vehicleDto
                .setHistory(historyMapper.mapToDto(vehicle.getHistory()));
        vehicleDto
                .setBasicInfo(basicInfoMapper.mapToDto(vehicle.getBasicInfo()));
        vehicleDto
                .setOrderInfo(orderInfoMapper.mapToDto(vehicle.getOrderInfo()));
        vehicleDto
                .setTechnicalData(technicalDataMapper.mapToDto(vehicle.getTechnicalData()));
        return vehicleDto;
    }

    @Override
    public Vehicle mapFromDto(VehicleDto vehicleDto){
        Vehicle vehicle = new Vehicle();
        vehicle.setMake(vehicleDto.getMake());
        vehicle.setModel(vehicleDto.getModel());
        vehicle.setVINCode(vehicleDto.getVINCode());
        vehicle.setConfiguration(vehicleDto.getConfiguration());
        vehicle
                .setSeller(userMapper.mapFromDto(vehicleDto.getSeller()));
        vehicle
                .setHistory(historyMapper.mapFromDto(vehicleDto.getHistory()));
        vehicle
                .setBasicInfo(basicInfoMapper.mapFromDto(vehicleDto.getBasicInfo()));
        vehicle
                .setOrderInfo(orderInfoMapper.mapFromDto(vehicleDto.getOrderInfo()));
        vehicle
                .setTechnicalData(technicalDataMapper.mapFromDto(vehicleDto.getTechnicalData()));
        return vehicle;
    }
}
