package jvlopes.bicycle.fleet.domain.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BicycleIDTest {

    @Test
    void validate() {
        assertDoesNotThrow(() -> new BicycleID("1234"));
        assertThrows(IllegalArgumentException.class, () -> new BicycleID(null));
        assertThrows(IllegalArgumentException.class, () -> new BicycleID(""));
    }
}