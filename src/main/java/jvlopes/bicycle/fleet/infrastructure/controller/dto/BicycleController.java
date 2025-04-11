package jvlopes.bicycle.fleet.infrastructure.controller.dto;

import jvlopes.bicycle.fleet.application.BicycleService;
import jvlopes.bicycle.fleet.domain.entity.Bicycle;

public class BicycleController {

    private final BicycleService bicycleService;

    public BicycleController(BicycleService bicycleService) {
        this.bicycleService = bicycleService;
    }

    public Bicycle create(Bicycle bicycle) {
        return bicycleService.save(bicycle);
    }

}
