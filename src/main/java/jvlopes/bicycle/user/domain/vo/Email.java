package jvlopes.bicycle.user.domain.vo;

public record Email(String value) {

    public Email {
        validate(value);
    }

    private void validate(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Email value cannot be null or empty");
        }
        // todo: email regex
    }
}
