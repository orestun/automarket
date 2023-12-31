package com.automarket.mapper;

import com.automarket.dto.VehicleHistoryDto;
import com.automarket.model.VehicleHistory;

public class VehicleHistoryMapper implements Mapper<VehicleHistory, VehicleHistoryDto> {

    @Override
    public VehicleHistoryDto mapToDto(VehicleHistory history){
        VehicleHistoryDto historyDto = new VehicleHistoryDto();
        historyDto.setMileage(history.getMileage());
        historyDto.setYear(history.getYear());
        historyDto.setWasInAccident(history.isWasInAccident());
        historyDto.setWasMoved(history.isWasMoved());
        historyDto.setMovedState(history.getMovedState());
        return historyDto;
    }

    @Override
    public VehicleHistory mapFromDto(VehicleHistoryDto historyDto){
        VehicleHistory history = new VehicleHistory();
        history.setMileage(historyDto.getMileage());
        history.setYear(historyDto.getYear());
        history.setWasInAccident(historyDto.isWasInAccident());
        history.setWasMoved(historyDto.isWasMoved());
        history.setMovedState(historyDto.getMovedState());
        return history;
    }
}
