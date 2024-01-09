package com.dmitriyevseyev.carmanager2.client.network;

import com.dmitriyevseyev.carmanager2.shared.Command;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class SendlerClient {
    private ObjectOutputStream objectOutputStream;
    private static SendlerClient instance;

    public static SendlerClient getInstance() {
        if (instance == null) {
            instance = new SendlerClient();
        }
        return instance;
    }

    public SendlerClient() {
    }

    public ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }

    public void setObjectOutputStream(ObjectOutputStream objectOutputStream) {
        this.objectOutputStream = objectOutputStream;
    }

    public void send (Command command) {
        try {
            objectOutputStream.writeObject(command);
            objectOutputStream.flush();

        } catch (IOException e) {
            System.out.println("SendlerClientError. " + e.getMessage());
        }
    }

    public void close () {
        try {
            objectOutputStream.close();
        } catch (IOException e) {
            System.out.println("SendlerClientError/ objectOutputStreamClose. " + e.getMessage());
        }
    }
}
