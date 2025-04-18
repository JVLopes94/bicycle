package jvlopes.bicycle.fleet.domain.entity;

import java.util.Objects;

public final class BicycleID {

    private final String id;

    public BicycleID(String id) {
        validate(id);
        this.id = id;
    }

    private void validate(String id) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("Invalid Bicycle ID");
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
}
