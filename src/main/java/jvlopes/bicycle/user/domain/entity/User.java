package jvlopes.bicycle.user.domain.entity;

import jvlopes.bicycle.fleet.domain.entity.Bicycle;
import jvlopes.bicycle.user.domain.vo.Email;

import java.time.LocalDateTime;
import java.util.List;

public final class User {

    private final String id;
    private final String name;
    private final Email email;
    private final LocalDateTime membershipStartDate;
    private final List<Bicycle> activeRentals;

    public User(
            String id,
            String name,
            Email email,
            LocalDateTime membershipStartDate,
            List<Bicycle> activeRentals
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.membershipStartDate = membershipStartDate;
        this.activeRentals = activeRentals;
    }
}
