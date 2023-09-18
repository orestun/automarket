package com.automarket.dto;

import com.automarket.utils.MovedState;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleHistoryDto {
    private int mileage;
    private int year;
    private boolean wasInAccident;
    private boolean wasMoved;
    private MovedState movedState;
}
