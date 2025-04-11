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
        validate(id, model, status, location, lastMaintenanceDate);
        this.id = id;
        this.model = model;
        this.status = status;
        this.location = location;
        this.lastMaintenanceDate = lastMaintenanceDate;
    }

    private void validate(String id, String model, BicycleStatus status, String location, LocalDateTime lastMaintenanceDate) {
        validateId(id);
        validateModel(model);
        validateStatus(status);
        validateLocation(location);
        validateLastMaintenanceDate(lastMaintenanceDate);
    }

    private void validateLastMaintenanceDate(LocalDateTime lastMaintenanceDate) {
        if (lastMaintenanceDate == null) throw new IllegalArgumentException("lastMaintenanceDate is null");
        if (lastMaintenanceDate.isAfter(LocalDateTime.now())) throw new IllegalArgumentException("lastMaintenanceDate is after now");
    }

    private void validateLocation(String location) {
        if (location == null || location.isEmpty()) throw new IllegalArgumentException("location is null or empty");
    }

    private void validateStatus(BicycleStatus status) {
        if (status == null) throw new IllegalArgumentException("status cannot be null");
    }

    private void validateModel(String model) {
        if (model == null || model.isEmpty()) throw new IllegalArgumentException("model is null or empty");
    }

    private void validateId(String id) {
        if (id == null || id.isEmpty()) throw new IllegalArgumentException("id is null or empty");
    }
}
