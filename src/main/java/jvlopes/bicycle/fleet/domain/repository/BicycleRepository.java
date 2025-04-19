package jvlopes.bicycle.fleet.domain.repository;

import jvlopes.bicycle.fleet.application.dto.PageResponse;
import jvlopes.bicycle.fleet.domain.entity.Bicycle;

public interface BicycleRepository {

    Bicycle save(Bicycle bicycle);

    PageResponse<Bicycle> findAll(int pageNumber, int pageSize);

}
