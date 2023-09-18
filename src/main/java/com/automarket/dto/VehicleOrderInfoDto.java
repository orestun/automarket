package com.automarket.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class VehicleOrderInfoDto {
    private BigDecimal price;
    private String country;
    private String state;
    private String city;
    private boolean isTradeInIncluded;
    private boolean isAuctionIncluded;
}
