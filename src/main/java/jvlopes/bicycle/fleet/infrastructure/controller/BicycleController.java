package jvlopes.bicycle.fleet.infrastructure.controller;

import jvlopes.bicycle.fleet.application.BicycleService;
import jvlopes.bicycle.fleet.domain.entity.Bicycle;
import jvlopes.bicycle.fleet.infrastructure.controller.dto.BicycledCreatedDTO;
import jvlopes.bicycle.fleet.infrastructure.controller.dto.CreateBicycleDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/bicycles")
public class BicycleController {

    private final BicycleService bicycleService;

    public BicycleController(BicycleService bicycleService) {
        this.bicycleService = bicycleService;
    }

    @PostMapping
    public BicycledCreatedDTO create(CreateBicycleDTO createBicycleDTO) {
        Bicycle bicycle = bicycleService.save(createBicycleDTO.toBicycle());
        return BicycledCreatedDTO.fromBicycle(bicycle);
    }

}
