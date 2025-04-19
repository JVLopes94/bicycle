package jvlopes.bicycle.fleet.application;

import jvlopes.bicycle.fleet.domain.entity.Bicycle;
import jvlopes.bicycle.fleet.domain.repository.BicycleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BicycleService {

    private final BicycleRepository repo;

    public BicycleService(BicycleRepository repo) {
        this.repo = repo;
    }

    public Bicycle save(Bicycle bicycle) {
        return repo.save(bicycle);
    }

    public Page<Bicycle> list(int page, int size) {
        return new PageImpl<>(List.of());
    }
}
