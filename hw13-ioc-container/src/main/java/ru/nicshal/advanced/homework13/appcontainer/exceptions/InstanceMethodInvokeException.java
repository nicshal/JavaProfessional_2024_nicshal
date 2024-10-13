package ru.nicshal.advanced.homework13.appcontainer.exceptions;

public class InstanceMethodInvokeException extends RuntimeException {

    public InstanceMethodInvokeException(String message, Throwable ex) {
        super(message, ex);
    }

    public InstanceMethodInvokeException(Throwable ex) {
        super(ex);
    }

    public InstanceMethodInvokeException(String message) {
        super(message);
    }

}
