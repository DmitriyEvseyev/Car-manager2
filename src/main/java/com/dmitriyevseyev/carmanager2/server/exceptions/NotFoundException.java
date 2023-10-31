package com.dmitriyevseyev.carmanager2.server.exceptions;

public class NotFoundException extends Exception {

    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
    }
}
