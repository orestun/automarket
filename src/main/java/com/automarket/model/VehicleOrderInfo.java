package com.automarket.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table
@Getter
@Setter
public class VehicleOrderInfo {
    @Id
    private Long id;
    private BigDecimal price;
    private String country;
    private String state;
    private String city;
    private boolean isTradeInIncluded;
    private boolean isAuctionIncluded;
}
