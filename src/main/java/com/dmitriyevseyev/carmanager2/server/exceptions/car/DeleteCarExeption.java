package com.dmitriyevseyev.carmanager2.server.exceptions.car;

public class DeleteCarExeption extends Exception{

    public DeleteCarExeption() {
        super();
    }

    public DeleteCarExeption(String message) {
        super(message);
    }
}