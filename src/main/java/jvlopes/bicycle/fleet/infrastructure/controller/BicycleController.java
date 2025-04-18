package jvlopes.bicycle.fleet.infrastructure.controller;

import jvlopes.bicycle.fleet.application.BicycleService;
import jvlopes.bicycle.fleet.domain.entity.Bicycle;
import jvlopes.bicycle.fleet.infrastructure.controller.dto.BicycledCreatedDTO;
import jvlopes.bicycle.fleet.infrastructure.controller.dto.CreateBicycleDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/bicycles")
public class BicycleController {

    private final BicycleService bicycleService;

    public BicycleController(BicycleService bicycleService) {
        this.bicycleService = bicycleService;
    }

    @PostMapping
    public ResponseEntity<BicycledCreatedDTO> create(CreateBicycleDTO createBicycleDTO) {
        Bicycle bicycle = bicycleService.save(createBicycleDTO.toBicycle());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(BicycledCreatedDTO.fromBicycle(bicycle));
    }

}
