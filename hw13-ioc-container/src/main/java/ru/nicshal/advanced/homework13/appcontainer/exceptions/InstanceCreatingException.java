package ru.nicshal.advanced.homework13.appcontainer.exceptions;

public class InstanceCreatingException extends RuntimeException {

    public InstanceCreatingException(String message, Throwable ex) {
        super(message, ex);
    }

    public InstanceCreatingException(Throwable ex) {
        super(ex);
    }

    public InstanceCreatingException(String message) {
        super(message);
    }

}
