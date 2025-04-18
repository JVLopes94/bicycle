package jvlopes.bicycle.fleet.infrastructure.controller.dto;

import jvlopes.bicycle.fleet.domain.entity.Bicycle;

public record BicycledCreatedDTO(
        String id,
        String model,
        String status,
        String location,
        String lastMaintenanceDate
) {
    public static BicycledCreatedDTO fromBicycle(Bicycle bicycle) {
        return new BicycledCreatedDTO(
                bicycle.getId().toString(),
                bicycle.getModel(),
                bicycle.getStatus().toString(),
                bicycle.getLocation(),
                bicycle.getLastMaintenanceDate().toString()
        );
    }
}
