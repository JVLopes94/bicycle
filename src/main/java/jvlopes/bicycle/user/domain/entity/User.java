package jvlopes.bicycle.user.domain.entity;

import jvlopes.bicycle.user.domain.vo.Email;

import java.time.LocalDateTime;
import java.util.List;
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
}
