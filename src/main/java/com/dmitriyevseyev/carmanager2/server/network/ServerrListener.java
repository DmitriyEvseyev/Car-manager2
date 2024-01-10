package com.dmitriyevseyev.carmanager2.server.network;

import com.dmitriyevseyev.carmanager2.client.network.CommandManagerClient;
import com.dmitriyevseyev.carmanager2.client.network.ListenerClient;
import com.dmitriyevseyev.carmanager2.shared.Command;

import java.io.IOException;
import java.io.ObjectInputStream;

public class ServerrListener extends Thread {
    private ObjectInputStream objectInputStream;
    private static ServerrListener instance;
    private boolean exit;

    public static ServerrListener getInstance() {
        if (instance == null) {
            instance = new ServerrListener();
        }
        return instance;
    }

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
                CommandManagerServer.getInstance().processCommand(request);
            }
            objectInputStream.close();
        } catch (IOException e) {
            System.out.println("ReadObjectServerError. " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundExceptionClassNotFoundExceptionServerError. " + e.getMessage());
        }
    }
}


