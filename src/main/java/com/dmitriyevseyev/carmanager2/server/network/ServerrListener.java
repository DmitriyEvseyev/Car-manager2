package com.dmitriyevseyev.carmanager2.server.network;

import com.dmitriyevseyev.carmanager2.shared.Command;

import java.io.IOException;
import java.io.ObjectInputStream;

public class ServerrListener extends Thread {
    private ObjectInputStream objectInputStream;
    private boolean exit;

    public ServerrListener() {
    }

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    public ObjectInputStream getObjectInputStream() {
        return objectInputStream;
    }

    public void setObjectInputStream(ObjectInputStream objectInputStream) {
        this.objectInputStream = objectInputStream;
    }

    public void run() {
        exit = true;
        Command request = null;
        try {
            while (exit) {
                request = (Command) objectInputStream.readObject();
                System.out.println("request - " + request);
                ServerCommandManager.getInstance().processCommand(request);
            }
            objectInputStream.close();
        } catch (IOException e) {
            System.out.println("ReadObjectServerError. " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundExceptionClassNotFoundExceptionServerError. " + e.getMessage());
        }
    }
}


