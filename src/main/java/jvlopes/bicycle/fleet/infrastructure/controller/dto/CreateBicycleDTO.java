package jvlopes.bicycle.fleet.infrastructure.controller.dto;

import jvlopes.bicycle.fleet.domain.entity.Bicycle;
import jvlopes.bicycle.fleet.domain.entity.BicycleID;
import jvlopes.bicycle.fleet.domain.vo.BicycleStatus;

import java.time.LocalDateTime;

public record CreateBicycleDTO(
        String id,
        String model,
        String status,
        String location,
        String lastMaintenanceDate
) {
    public Bicycle toBicycle() {
        return new Bicycle(
                new BicycleID(id),
                model,
                BicycleStatus.valueOf(status),
                location,
                LocalDateTime.parse(lastMaintenanceDate) // 2011-12-03T10:15:30
        );
    }
}
