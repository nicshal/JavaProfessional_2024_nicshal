package ru.nicshal.advanced.homework13.appcontainer.exceptions;

public class TooManyBeanException extends RuntimeException {

    public TooManyBeanException(String message, Throwable ex) {
        super(message, ex);
    }

    public TooManyBeanException(Throwable ex) {
        super(ex);
    }

    public TooManyBeanException(String message) {
        super(message);
    }

}
