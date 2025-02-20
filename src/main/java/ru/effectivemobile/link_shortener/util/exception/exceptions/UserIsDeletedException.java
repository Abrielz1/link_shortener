package ru.effectivemobile.link_shortener.util.exception.exceptions;

public class UserIsDeletedException extends RuntimeException {

    public UserIsDeletedException(String message) {
        super((message));
    }
}
