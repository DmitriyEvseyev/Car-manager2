package com.dmitriyevseyev.carmanager2.client.network;

import com.dmitriyevseyev.carmanager2.shared.Command;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class ClientSendler {
    private ObjectOutputStream objectOutputStream;
    private static ClientSendler instance;

    public static ClientSendler getInstance() {
        if (instance == null) {
            instance = new ClientSendler();
        }
        return instance;
    }

    public ClientSendler() {
    }

    public void setObjectOutputStream(ObjectOutputStream objectOutputStream) {
        this.objectOutputStream = objectOutputStream;
    }

    public void send(Command command) {
        try {
            objectOutputStream.writeObject(command);
            objectOutputStream.flush();

        } catch (IOException e) {
            System.out.println("SendlerClientError. " + e.getMessage());
        }
    }

    public void close() {
        try {
            objectOutputStream.close();
        } catch (IOException e) {
            System.out.println("SendlerClientError/ objectOutputStreamClose. " + e.getMessage());
        }
    }
}
