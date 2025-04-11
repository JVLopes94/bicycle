package jvlopes.bicycle.fleet.infrastructure.controller.dto;

import jvlopes.bicycle.fleet.domain.entity.Bicycle;

import java.util.UUID;

public record BicycledCreatedDTO(
        UUID id,
        String model,
        String status,
        String location,
        String lastMaintenanceDate
) {
    public static BicycledCreatedDTO fromBicycle(Bicycle bicycle) {
        return new BicycledCreatedDTO(
                bicycle.getId(),
                bicycle.getModel(),
                bicycle.getStatus().toString(),
                bicycle.getLocation(),
                bicycle.getLastMaintenanceDate().toString()
        );
    }
}
