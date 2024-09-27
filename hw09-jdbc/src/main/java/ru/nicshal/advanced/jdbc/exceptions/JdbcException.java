package ru.nicshal.advanced.jdbc.exceptions;

public class JdbcException extends RuntimeException {

    public JdbcException(String message, Throwable ex) {
        super(message, ex);
    }

    public JdbcException(Throwable ex) {
        super(ex);
    }

    public JdbcException(String message) {
        super(message);
    }

}
