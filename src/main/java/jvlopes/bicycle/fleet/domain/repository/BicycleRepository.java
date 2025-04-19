package jvlopes.bicycle.fleet.domain.repository;

import jvlopes.bicycle.fleet.application.dto.PageResponse;
import jvlopes.bicycle.fleet.domain.entity.Bicycle;
import jvlopes.bicycle.fleet.domain.entity.BicycleID;
import jvlopes.bicycle.fleet.domain.vo.BicycleStatus;

public interface BicycleRepository {

    Bicycle save(Bicycle bicycle);

    PageResponse<Bicycle> findAll(int pageNumber, int pageSize);

    PageResponse<Bicycle> findAllByStatus(BicycleStatus status, int page, int size);

    Bicycle findByID(BicycleID bicycleID);
}
