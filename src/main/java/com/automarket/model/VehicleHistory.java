package com.automarket.model;

import com.automarket.utils.MovedState;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "vehicle_history")
@Getter
@Setter
public class VehicleHistory {

    @Id
    private Long id;
    @NotNull(message = "Mileage can`t be null")
    private int mileage;
    @NotNull(message = "Year can`t be null")
    private int year;
    private boolean wasInAccident;
    private boolean wasMoved;
    private MovedState movedState;
}
