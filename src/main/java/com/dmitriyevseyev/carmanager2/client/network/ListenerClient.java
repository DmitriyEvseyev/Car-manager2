package com.dmitriyevseyev.carmanager2.client.network;

import com.dmitriyevseyev.carmanager2.shared.Command;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ListenerClient {
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

    public void read() {
        try {
            Command responce = (Command) objectInputStream.readObject();
            CommandManagerClient.getInstance().processCommand(responce);
            System.out.println("ListenerClient responce - " + responce);

        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
