package ru.effectivemobile.util.exception.exceptions;

public class UserIsDeletedException extends RuntimeException {

    public UserIsDeletedException(String message) {
        super((message));
    }
}
