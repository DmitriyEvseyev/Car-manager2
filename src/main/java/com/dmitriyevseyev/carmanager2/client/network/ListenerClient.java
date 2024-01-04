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
        while (true) {
            Command responce = null;
            try {
                /*try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("ListenerSleepEx - " + e.getMessage());
                }

                 */
                responce = (Command) objectInputStream.readObject();
                System.out.println("ListenerClient + " + responce);
            } catch (IOException e) {
                System.out.println("ListenerClient error, IOException." + e.getMessage());
            } catch (ClassNotFoundException e) {
                System.out.println("ListenerClient error, ClassNotFoundException" + e.getMessage());
            }
            CommandManagerClient.getInstance().processCommand(responce);
        }
    }
}

