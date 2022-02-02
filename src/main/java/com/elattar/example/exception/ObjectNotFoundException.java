package com.elattar.example.exception;

public class ObjectNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 4620952515662818311L;

    public Class<?> objectClass;

    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjectNotFoundException(String message) {
        super(message);
    }

    public Class<?> getObjectClass() {
        return objectClass;
    }
}
