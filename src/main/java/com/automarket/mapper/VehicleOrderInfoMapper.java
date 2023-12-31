package com.automarket.mapper;

import com.automarket.dto.VehicleOrderInfoDto;
import com.automarket.model.VehicleOrderInfo;

public class VehicleOrderInfoMapper implements Mapper<VehicleOrderInfo, VehicleOrderInfoDto>{

    @Override
    public VehicleOrderInfoDto mapToDto(VehicleOrderInfo orderInfo){
        VehicleOrderInfoDto orderInfoDto = new VehicleOrderInfoDto();
        orderInfoDto.setCountry(orderInfo.getCountry());
        orderInfoDto.setState(orderInfo.getState());
        orderInfoDto.setCity(orderInfo.getCity());
        orderInfoDto.setPrice(orderInfo.getPrice());
        orderInfoDto.setTradeInIncluded(orderInfo.isTradeInIncluded());
        orderInfoDto.setAuctionIncluded(orderInfo.isAuctionIncluded());
        return orderInfoDto;
    }

    @Override
    public VehicleOrderInfo mapFromDto(VehicleOrderInfoDto orderInfoDto){
        VehicleOrderInfo orderInfo = new VehicleOrderInfo();
        orderInfo.setState(orderInfoDto.getState());
        orderInfo.setCountry(orderInfoDto.getCountry());
        orderInfo.setCity(orderInfoDto.getCity());
        orderInfo.setPrice(orderInfoDto.getPrice());
        orderInfo.setTradeInIncluded(orderInfoDto.isTradeInIncluded());
        orderInfo.setAuctionIncluded(orderInfoDto.isAuctionIncluded());
        return orderInfo;
    }
}
