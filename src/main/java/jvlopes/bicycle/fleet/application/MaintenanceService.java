package jvlopes.bicycle.fleet.application;

import jvlopes.bicycle.fleet.domain.entity.Bicycle;
import org.springframework.stereotype.Service;

@Service
public class MaintenanceService {

    private final BicycleService bicycleService;

    public MaintenanceService(BicycleService bicycleService) {
        this.bicycleService = bicycleService;
    }

    public Bicycle putBicycleUnderMaintenance(String bicycleID) {
        Bicycle bicycle = bicycleService.getByID(bicycleID);
        bicycle.putUnderMaintenance();
        bicycleService.save(bicycle);
        return bicycle;
    }

    public Bicycle putBicycleAvailable(String bicycleID) {
        return null;
    }

}
