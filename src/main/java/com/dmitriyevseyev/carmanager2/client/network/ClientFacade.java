package com.dmitriyevseyev.carmanager2.client.network;

import java.io.*;
import java.net.Socket;

import static com.dmitriyevseyev.carmanager2.shared.utils.Constants.SERVER_PORT;
import static com.dmitriyevseyev.carmanager2.shared.utils.Constants.SERVER_URL;

public class ClientFacade {
    private static ClientFacade instance;
    private Socket socket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private ClientSendler clientSendler;
    private ClientListener clientListener;

    public static ClientFacade getInstance() {
        if (instance == null) {
            instance = new ClientFacade();
        }
        return instance;
    }

    private ClientFacade() {
    }

    public void connect() {
        try {
            this.socket = new Socket(SERVER_URL, Integer.parseInt(SERVER_PORT));
            System.out.println("The client is running!");
        } catch (IOException e) {
            System.out.println("ConnectionClientError. " + e.getMessage());
        }
        try {
            this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            this.objectInputStream = new ObjectInputStream(socket.getInputStream());

        } catch (IOException e) {
            System.out.println("ClientStreamError. " + e.getMessage());
        }
        clientSendler = ClientSendler.getInstance();
        clientSendler.setObjectOutputStream(objectOutputStream);

        clientListener = ClientListener.getInstance();
        clientListener.setObjectInputStream(objectInputStream);

        clientListener.start();
    }
}


