package com.automarket.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class VehicleDto {

    private String make;
    private String model;
    private String configuration;
    private LocalDateTime createTimestamp;
    private LocalDateTime updateTimeStamp;
    private String VINCode;
    private UserDto seller;
    private VehicleHistoryDto history;
    private VehicleTechnicalDataDto technicalData;
    private VehicleOrderInfoDto orderInfo;
    private VehicleBasicInfoDto basicInfo;
}
