package jvlopes.bicycle.factory;

import jvlopes.bicycle.fleet.application.dto.PageResponse;
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
        PageResponse<Bicycle> emptyResponse = new PageResponse<>(
                List.of(), 0, 0, 0, 10
        );
        List<Bicycle> content = List.of(createBicycleWithID(new BicycleID("1234")));
        PageResponse<Bicycle> responseWithOneItem = new PageResponse<>(
                content, 1, 1, 0, 10
        );
        List<Bicycle> content2 = List.of(
                createBicycleWithID(new BicycleID("1234")),
                createBicycleWithID(new BicycleID("5678"))
        );
        PageResponse<Bicycle> responseWithTwoItems = new PageResponse<>(
                content2, 2, 1, 0, 10
        );
        return Stream.of(
                Arguments.of(emptyResponse),
                Arguments.of(responseWithOneItem),
                Arguments.of(responseWithTwoItems)
        );
    }

}
