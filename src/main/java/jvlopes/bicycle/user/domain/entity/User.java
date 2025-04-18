package jvlopes.bicycle.user.domain.entity;

import jvlopes.bicycle.user.domain.vo.Email;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public final class User {

    private final UUID id;
    private final String name;
    private final Email email;
    private final LocalDateTime membershipStartDate;
    private final List<UUID> activeRentals;

    public User(
            UUID id,
            String name,
            Email email,
            LocalDateTime membershipStartDate,
            List<UUID> activeRentals
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.membershipStartDate = membershipStartDate;
        this.activeRentals = activeRentals;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
