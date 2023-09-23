package com.automarket.service;

import com.automarket.dto.*;
import com.automarket.exception.DataValidationHandler;
import com.automarket.exception.HibernateValidationException;
import com.automarket.exception.ItemNotFoundException;
import com.automarket.exception.ObjectAlreadyExistsException;
import com.automarket.mapper.*;
import com.automarket.model.*;
import com.automarket.repository.VehicleCriteriaRepository;
import com.automarket.repository.VehicleRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleCriteriaRepository vehicleCriteriaRepository;
    private final VehicleMapper vehicleMapper = new VehicleMapper();
    private final VehicleOrderInfoMapper orderInfoMapper = new VehicleOrderInfoMapper();
    private final VehicleBasicInfoMapper basicInfoMapper = new VehicleBasicInfoMapper();
    private final VehicleTechnicalDataMapper technicalDataMapper = new VehicleTechnicalDataMapper();
    private final VehicleHistoryMapper historyMapper = new VehicleHistoryMapper();
    public VehicleService(VehicleRepository carRepository, VehicleCriteriaRepository vehicleCriteriaRepository) {
        this.vehicleRepository = carRepository;
        this.vehicleCriteriaRepository = vehicleCriteriaRepository;
    }

    public List<VehicleDto> getAllVehicles(int page, int pageSize){
        if(page > 0){
            page--;
        }else{
            page = 0;
        }
        return vehicleRepository
                .findAll(PageRequest.of(page, pageSize))
                .map(vehicleMapper::mapToDto)
                .toList();
    }

    public VehicleDto addCar(Vehicle vehicle) {
        validateVehicle(vehicle);
        validateVINCode(vehicle.getVINCode());
        vehicleRepository.save(vehicle);
        return vehicleMapper.mapToDto(vehicle);
    }

    public VehicleDto updateVehicleById(long id, Vehicle vehicle) {
        validateVehicle(vehicle);
        validateVINCode(vehicle.getVINCode());
        validateIdExistence(id);
        vehicle.setId(id);
        vehicleRepository.save(vehicle);
        return vehicleMapper.mapToDto(vehicle);
    }

    public ResponseEntity<?> deleteVehicleById(long id) {
        validateIdExistence(id);
        vehicleRepository.deleteById(id);
        String responseMessage = String.format("Successful deleted vehicle with id - '%d'", id);
        return new ResponseEntity<>(Map.of("message", responseMessage) , HttpStatus.OK);
    }

    public VehicleOrderInfoDto updateVehicleOrderInfoById(long id, VehicleOrderInfoDto orderInfoDto) {
        validateIdExistence(id);
        Vehicle vehicle = vehicleRepository.findById(id);
        VehicleOrderInfo orderInfo = orderInfoMapper.mapFromDto(orderInfoDto);
        vehicle.setOrderInfo(orderInfo);
        return orderInfoDto;
    }

    public VehicleBasicInfoDto updateVehicleBasicInfoById(long id, VehicleBasicInfoDto basicInfoDto) {
        validateIdExistence(id);
        Vehicle vehicle = vehicleRepository.findById(id);
        VehicleBasicInfo basicInfo = basicInfoMapper.mapFromDto(basicInfoDto);
        vehicle.setBasicInfo(basicInfo);
        return basicInfoDto;
    }

    public VehicleTechnicalDataDto updateVehicleTechnicalDataById(long id, VehicleTechnicalDataDto technicalDataDto) {
        validateIdExistence(id);
        Vehicle vehicle = vehicleRepository.findById(id);
        VehicleTechnicalData technicalData = technicalDataMapper.mapFromDto(technicalDataDto);
        vehicle.setTechnicalData(technicalData);
        return technicalDataDto;
    }

    public VehicleHistoryDto updateVehicleHistoryById(long id, VehicleHistoryDto historyDto) {
        validateIdExistence(id);
        Vehicle vehicle = vehicleRepository.findById(id);
        VehicleHistory history = historyMapper.mapFromDto(historyDto);
        vehicle.setHistory(history);
        return historyDto;
    }

    private void validateVehicle(@Valid Vehicle vehicle){
        DataValidationHandler<Vehicle> validationHandler = new DataValidationHandler<>();
        String validationErrorsMessage = validationHandler.errorsRepresentation(vehicle);
        if (!validationErrorsMessage.isEmpty()){
            throw new HibernateValidationException(validationErrorsMessage, 40001L);
        }
    }

    private void validateVINCode(String VINCode){
        if(vehicleRepository.existsByVINCode(VINCode)){
            String message = String.format("Vehicle with such VIN ='%s' already exist", VINCode);
            throw new ObjectAlreadyExistsException(message, 40901L);
        }
    }

    private void validateIdExistence(long id){
        if(!vehicleRepository.existsById(id)){
            String message = String.format("Vehicle with such id - '%d' not exist", id);
            throw new ItemNotFoundException(message, 40401L);
        }
    }

    public List<VehicleDto> getAllVehiclesByCriteria(VehicleSearchCriteriaDto criteria, int page, int pageSize) {
        if(page > 0){
            page--;
        }else{
            page = 0;
        }
        Pageable pageable = PageRequest.of(page, pageSize);
        return vehicleCriteriaRepository
                .getVehiclesWithAllCriteria(criteria, pageable)
                .map(vehicleMapper::mapToDto)
                .toList();
    }
}
