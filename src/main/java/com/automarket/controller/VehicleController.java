package com.automarket.controller;

import com.automarket.dto.*;
import com.automarket.mapper.VehicleMapper;
import com.automarket.model.Vehicle;
import com.automarket.service.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "vehicle")
public class VehicleController {

    private final VehicleService vehicleService;
    private final VehicleMapper vehicleMapper = new VehicleMapper();

    public VehicleController(VehicleService carService) {
        this.vehicleService = carService;
    }

    @PostMapping
    public VehicleDto addCar(@RequestBody VehicleDto vehicleDto){
        return vehicleService
                .addCar(vehicleMapper.mapFromDto(vehicleDto));
    }

    @GetMapping
    public List<VehicleDto> getVehicles(
            @RequestParam(name = "page", defaultValue = "1", required = false) int page,
            @RequestParam(name = "page-size", defaultValue = "10", required = false) int pageSize){
        return vehicleService
                .getAllVehicles(page, pageSize);
    }

    @PatchMapping("{id}")
    public VehicleDto updateVehicle(
            @PathVariable(name = "id") long id,
            @RequestBody VehicleDto vehicleDto){
        Vehicle vehicle = vehicleMapper.mapFromDto(vehicleDto);
        return vehicleService
                .updateVehicleById(id, vehicle);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteVehicleById(
            @PathVariable(name = "id") long id){
        return vehicleService.deleteVehicleById(id);
    }

    @GetMapping("by-criteria")
    public List<VehicleDto> getVehiclesByCriteria(
            @RequestBody VehicleSearchCriteriaDto criteria,
            @RequestParam(name = "page", defaultValue = "1", required = false) int page,
            @RequestParam(name = "page-size", defaultValue = "10", required = false) int pageSize){
        return vehicleService.getAllVehiclesByCriteria(criteria, page, pageSize);
    }

    @PatchMapping("update-order-info/{id}")
    public VehicleOrderInfoDto updateVehicleOrderInfoById(
            @PathVariable(name = "id") long id,
            @RequestBody VehicleOrderInfoDto orderInfoDto){
        return vehicleService.updateVehicleOrderInfoById(id, orderInfoDto);
    }

    @PatchMapping("update-basic-info/{id}")
    public VehicleBasicInfoDto updateVehicleBasicInfoById(
            @PathVariable(name = "id") long id,
            @RequestBody VehicleBasicInfoDto basicInfoDto){
        return vehicleService.updateVehicleBasicInfoById(id, basicInfoDto);
    }

    @PatchMapping("update-technical-data/{id}")
    public VehicleTechnicalDataDto updateVehicleTechnicalDataById(
            @PathVariable(name = "id") long id,
            @RequestBody VehicleTechnicalDataDto technicalDataDto){
        return vehicleService.updateVehicleTechnicalDataById(id, technicalDataDto);
    }

    @PatchMapping("update-history/{id}")
    public VehicleHistoryDto updateVehicleHistoryById(
            @PathVariable(name = "id") long id,
            @RequestBody VehicleHistoryDto vehicleHistoryDto){
        return vehicleService.updateVehicleHistoryById(id, vehicleHistoryDto);
    }

    @GetMapping("{id}")
    public VehicleDto getVehicleById(
            @PathVariable(name = "id") long id){
        return vehicleService.getVehicleById(id);
    }
}
