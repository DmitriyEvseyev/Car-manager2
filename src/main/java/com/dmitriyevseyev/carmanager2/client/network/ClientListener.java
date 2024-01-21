package com.dmitriyevseyev.carmanager2.client.network;

import com.dmitriyevseyev.carmanager2.shared.model.Command;

import java.io.IOException;
import java.io.ObjectInputStream;

public class ClientListener extends Thread {
    private ObjectInputStream objectInputStream;
    private static ClientListener instance;
    private boolean exit;

    public static ClientListener getInstance() {
        if (instance == null) {
            instance = new ClientListener();
        }
        return instance;
    }

    public ClientListener() {
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    public void setObjectInputStream(ObjectInputStream objectInputStream) {
        this.objectInputStream = objectInputStream;
    }

    public void run() {
        exit = true;
        try {
            while (exit) {
                Command responce = (Command) objectInputStream.readObject();
                System.out.println("ListenerClient + " + responce);
                ClientCommandManager.getInstance().processCommand(responce);
            }
        } catch (IOException e) {
            System.out.println("ListenerClient error, IOException. " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("ListenerClient error, ClassNotFoundException" + e.getMessage());
        }
    }
}


