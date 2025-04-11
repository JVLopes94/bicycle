package jvlopes.bicycle.fleet.infrastructure.adapter;

import jvlopes.bicycle.fleet.domain.entity.Bicycle;
import jvlopes.bicycle.fleet.domain.repository.BicycleRepository;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcBicycleRepositoryAdapter implements BicycleRepository {
    @Override
    public Bicycle save(Bicycle bicycle) {
        return null;
    }
}
