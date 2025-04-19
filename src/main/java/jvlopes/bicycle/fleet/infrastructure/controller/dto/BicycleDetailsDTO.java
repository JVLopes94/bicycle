package jvlopes.bicycle.fleet.infrastructure.controller.dto;

import jvlopes.bicycle.fleet.domain.entity.Bicycle;

public record BicycleDetailsDTO(
        String id,
        String model,
        String status,
        String location,
        String lastMaintenanceDate
) {
    public static BicycleDetailsDTO fromBicycle(Bicycle bicycle) {
        return new BicycleDetailsDTO(
                bicycle.getId().toString(),
                bicycle.getModel(),
                bicycle.getStatus().toString(),
                bicycle.getLocation(),
                bicycle.getLastMaintenanceDate().toString()
        );
    }
}
