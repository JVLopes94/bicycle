package jvlopes.bicycle.factory;

import jvlopes.bicycle.fleet.domain.entity.Bicycle;
import jvlopes.bicycle.fleet.domain.entity.BicycleID;
import jvlopes.bicycle.fleet.domain.vo.BicycleStatus;

import java.time.LocalDateTime;

public class BicycleTestFactory {

    public static Bicycle createBicycle() {
        return new Bicycle(
                new BicycleID("1234"),
                "model",
                BicycleStatus.AVAILABLE,
                "SP",
                LocalDateTime.now()
        );
    }

}
