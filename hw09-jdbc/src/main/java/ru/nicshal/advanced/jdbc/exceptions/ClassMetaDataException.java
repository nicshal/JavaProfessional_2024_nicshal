package ru.nicshal.advanced.jdbc.exceptions;

public class ClassMetaDataException extends RuntimeException {

    public ClassMetaDataException(String message, Throwable ex) {
        super(message, ex);
    }

    public ClassMetaDataException(Throwable ex) {
        super(ex);
    }

    public ClassMetaDataException(String message) {
        super(message);
    }

}
