package ru.skillbox.exception;

public class UserIdNotConsistentException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UserIdNotConsistentException() {}

    public UserIdNotConsistentException(String message) {
        super(message);
    }
}
