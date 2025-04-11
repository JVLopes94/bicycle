package jvlopes.bicycle.fleet.application;

import jvlopes.bicycle.fleet.domain.entity.Bicycle;
import jvlopes.bicycle.fleet.domain.repository.BicycleRepository;

public class BicycleService {

    private final BicycleRepository repo;

    public BicycleService(BicycleRepository repo) {
        this.repo = repo;
    }

    public Bicycle save(Bicycle bicycle) {
        return repo.save(bicycle);
    }

}
