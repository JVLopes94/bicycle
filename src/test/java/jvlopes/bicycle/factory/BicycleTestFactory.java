package jvlopes.bicycle.factory;

import jvlopes.bicycle.fleet.domain.entity.Bicycle;
import jvlopes.bicycle.fleet.domain.entity.BicycleID;
import jvlopes.bicycle.fleet.domain.vo.BicycleStatus;
import org.junit.jupiter.params.provider.Arguments;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

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

    public static Bicycle createBicycleWithID(BicycleID bicycleID) {
        return new Bicycle(
                bicycleID,
                "model",
                BicycleStatus.AVAILABLE,
                "SP",
                LocalDateTime.now()
        );
    }

    public static Stream<Arguments> bicycleListProvider() {
        return Stream.of(
                Arguments.of(
                        List.of()),
                Arguments.of(
                        List.of(createBicycleWithID(new BicycleID("1234")))),
                Arguments.of(
                        List.of(createBicycleWithID(new BicycleID("1234")), createBicycleWithID(new BicycleID("5678"))))
        );
    }

}
