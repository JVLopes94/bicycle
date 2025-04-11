package jvlopes.bicycle.fleet.domain.entity;

import jvlopes.bicycle.fleet.domain.vo.BicycleStatus;

import java.time.LocalDateTime;

public final class Bicycle {

    private final String id;
    private final String model;
    private final BicycleStatus status;
    private final String location;
    private final LocalDateTime lastMaintenanceDate;

    public Bicycle(
            String id,
            String model,
            BicycleStatus status,
            String location,
            LocalDateTime lastMaintenanceDate
    ) {
        this.id = id;
        this.model = model;
        this.status = status;
        this.location = location;
        this.lastMaintenanceDate = lastMaintenanceDate;
    }
}
