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
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
