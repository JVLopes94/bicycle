package jvlopes.bicycle.fleet.domain.entity;

import jvlopes.bicycle.fleet.domain.exception.InvalidBicycleIdException;

import java.util.Objects;

public final class BicycleID {

    private final String id;

    public BicycleID(String id) {
        validate(id);
        this.id = id;
    }

    private void validate(String id) {
        if (id == null || id.isBlank()) throw new InvalidBicycleIdException();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BicycleID bicycleID = (BicycleID) o;
        return Objects.equals(id, bicycleID.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return id;
    }
}
