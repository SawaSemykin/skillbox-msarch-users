package ru.skillbox.exception;

public class UserIdNotProvidedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UserIdNotProvidedException() {}

    public UserIdNotProvidedException(String message) {
        super(message);
    }
}
