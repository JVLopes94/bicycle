package jvlopes.bicycle.fleet.infrastructure.controller.dto;

import jvlopes.bicycle.fleet.domain.entity.Bicycle;

public record BicycleCreatedDTO(
        String id,
        String model,
        String status,
        String location,
        String lastMaintenanceDate
) {
    public static BicycleCreatedDTO fromBicycle(Bicycle bicycle) {
        return new BicycleCreatedDTO(
                bicycle.getId().toString(),
                bicycle.getModel(),
                bicycle.getStatus().toString(),
                bicycle.getLocation(),
                bicycle.getLastMaintenanceDate().toString()
        );
    }
}
