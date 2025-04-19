package jvlopes.bicycle.fleet.infrastructure.controller;

import jvlopes.bicycle.fleet.application.BicycleService;
import jvlopes.bicycle.fleet.domain.entity.Bicycle;
import jvlopes.bicycle.fleet.infrastructure.controller.dto.BicycleCreatedDTO;
import jvlopes.bicycle.fleet.infrastructure.controller.dto.CreateBicycleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController("/bicycles")
public class BicycleController {

    private final BicycleService bicycleService;

    public BicycleController(BicycleService bicycleService) {
        this.bicycleService = bicycleService;
    }

    @PostMapping
    public ResponseEntity<BicycleCreatedDTO> create(CreateBicycleDTO createBicycleDTO) {
        Bicycle bicycle = bicycleService.save(createBicycleDTO.toBicycle());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .location(URI.create("/bicycles/" + bicycle.getId()))
                .body(BicycleCreatedDTO.fromBicycle(bicycle));
    }

    public ResponseEntity<Page<BicycleCreatedDTO>> list() {
        return ResponseEntity.ok(new PageImpl<>(List.of()));
    }
}
