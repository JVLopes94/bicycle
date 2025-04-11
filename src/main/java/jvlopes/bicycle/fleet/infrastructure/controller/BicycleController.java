package jvlopes.bicycle.fleet.infrastructure.controller;

import jvlopes.bicycle.fleet.application.BicycleService;
import jvlopes.bicycle.fleet.domain.entity.Bicycle;
import jvlopes.bicycle.fleet.infrastructure.controller.dto.BicycledCreatedDTO;
import jvlopes.bicycle.fleet.infrastructure.controller.dto.CreateBicycleDTO;

public class BicycleController {

    private final BicycleService bicycleService;

    public BicycleController(BicycleService bicycleService) {
        this.bicycleService = bicycleService;
    }

    public BicycledCreatedDTO create(CreateBicycleDTO createBicycleDTO) {
        Bicycle bicycle = bicycleService.save(createBicycleDTO.toBicycle());
        return BicycledCreatedDTO.fromBicycle(bicycle);
    }

}
