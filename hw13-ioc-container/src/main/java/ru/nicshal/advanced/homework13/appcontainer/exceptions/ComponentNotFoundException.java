package ru.nicshal.advanced.homework13.appcontainer.exceptions;

public class ComponentNotFoundException extends RuntimeException {

    public ComponentNotFoundException(String message, Throwable ex) {
        super(message, ex);
    }

    public ComponentNotFoundException(Throwable ex) {
        super(ex);
    }

    public ComponentNotFoundException(String message) {
        super(message);
    }

}
