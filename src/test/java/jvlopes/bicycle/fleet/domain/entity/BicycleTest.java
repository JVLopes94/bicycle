package jvlopes.bicycle.fleet.domain.entity;

import jvlopes.bicycle.fleet.domain.vo.BicycleStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;

class BicycleTest {

    @Test
    public void validateInvalidId() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Bicycle(
                        null,
                        "model",
                        BicycleStatus.AVAILABLE,
                        "Sao Paulo",
                        LocalDateTime.now().minusDays(15)
                )
        );
        assertThrows(
                IllegalArgumentException.class,
                () -> new Bicycle(
                        "",
                        "model",
                        BicycleStatus.AVAILABLE,
                        "Sao Paulo",
                        LocalDateTime.now().minusDays(15)
                )
        );
    }

    @Test
    public void validateInvalidModel() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Bicycle(
                        "abc123",
                        null,
                        BicycleStatus.AVAILABLE,
                        "Sao Paulo",
                        LocalDateTime.now().minusDays(15)
                )
        );
        assertThrows(
                IllegalArgumentException.class,
                () -> new Bicycle(
                        "abc123",
                        "",
                        BicycleStatus.AVAILABLE,
                        "Sao Paulo",
                        LocalDateTime.now().minusDays(15)
                )
        );
    }

    @Test
    public void validateInvalidStatus() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Bicycle(
                        "abc123",
                        "model",
                        null,
                        "Sao Paulo",
                        LocalDateTime.now().minusDays(15)
                )
        );
    }

    @Test
    public void validateInvalidLocation() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Bicycle(
                        "abc123",
                        "model",
                        BicycleStatus.AVAILABLE,
                        null,
                        LocalDateTime.now().minusDays(15)
                )
        );
        assertThrows(
                IllegalArgumentException.class,
                () -> new Bicycle(
                        "abc123",
                        "model",
                        BicycleStatus.AVAILABLE,
                        "",
                        LocalDateTime.now().minusDays(15)
                )
        );
    }

    @Test
    public void validateInvalidLastMaintenanceDate() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Bicycle(
                        "abc123",
                        "model",
                        BicycleStatus.AVAILABLE,
                        "Sao Paulo",
                        null
                )
        );
        assertThrows(
                IllegalArgumentException.class,
                () -> new Bicycle(
                        "abc123",
                        "model",
                        BicycleStatus.AVAILABLE,
                        "Sao Paulo",
                        LocalDateTime.now().plusDays(15)
                )
        );
    }

}