package com.dmitriyevseyev.carmanager2.client.network;

import com.dmitriyevseyev.carmanager2.shared.Command;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ListenerClient extends Thread {
    private ObjectInputStream objectInputStream;
    private static ListenerClient instance;

    public static ListenerClient getInstance() {
        if (instance == null) {
            instance = new ListenerClient();
        }
        return instance;
    }

    public ListenerClient() {
    }

    public ObjectInputStream getObjectInputStream() {
        return objectInputStream;
    }

    public void setObjectInputStream(ObjectInputStream objectInputStream) {
        this.objectInputStream = objectInputStream;
    }

    public void run() {
        try {
            while (!this.isInterrupted()) {
                Command responce = (Command) objectInputStream.readObject();
                System.out.println("ListenerClient + " + responce);
                CommandManagerClient.getInstance().processCommand(responce);
            }
            objectInputStream.close();
        } catch (IOException e) {
            System.out.println("ListenerClient error, IOException. " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("ListenerClient error, ClassNotFoundException" + e.getMessage());
        }
    }
}


