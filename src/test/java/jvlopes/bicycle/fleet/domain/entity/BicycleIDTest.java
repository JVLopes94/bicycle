package jvlopes.bicycle.fleet.domain.entity;

import jvlopes.bicycle.fleet.domain.exception.InvalidBicycleIdException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BicycleIDTest {

    @Test
    void validate() {
        assertDoesNotThrow(() -> new BicycleID("1234"));
        assertThrows(InvalidBicycleIdException.class, () -> new BicycleID(null));
        assertThrows(InvalidBicycleIdException.class, () -> new BicycleID(""));
    }
}