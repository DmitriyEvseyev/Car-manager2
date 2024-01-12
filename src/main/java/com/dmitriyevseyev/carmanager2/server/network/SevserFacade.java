package com.dmitriyevseyev.carmanager2.server.network;

import com.dmitriyevseyev.carmanager2.shared.Command;
import com.dmitriyevseyev.carmanager2.shared.CommandId;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static com.dmitriyevseyev.carmanager2.shared.Constants.SERVER_PORT;

public class SevserFacade {
    private static SevserFacade instance;
    private static Socket clientSocket;
    private static ServerSocket server;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private ServerrListener serverrListener;
    private ServerSendler serverSendler;

    public static SevserFacade getInstance() {
        if (instance == null) {
            instance = new SevserFacade();
        }
        return instance;
    }

    private SevserFacade() {
    }

    public void disconnectClient() {
        serverrListener.setExit(false);
        serverrListener.interrupt();
    }

    public void connect() {
        try {
            server = new ServerSocket(Integer.parseInt(SERVER_PORT));
            System.out.println("The server is running!");

        } catch (IOException e) {
            System.out.println("serverSocketRunError. " + e.getMessage());
        }
        while (true) {
            try {
                clientSocket = server.accept();
                System.out.println("client connected");
            } catch (IOException e) {
                System.out.println("clientConnectedError. " + e.getMessage());
            }
            try {
                this.objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
                this.objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            } catch (IOException e) {
                System.out.println("serverStreamError. " + e.getMessage());
            }
            serverSendler = ServerSendler.getInstance();
            serverSendler.setObjectOutputStream(objectOutputStream);

            ServerCommandManager.getInstance().processCommand(new Command(CommandId.GET_ALL_CARS, ""));

            serverrListener = new ServerrListener();
            serverrListener.setObjectInputStream(objectInputStream);
            serverrListener.start();
        }
    }
}
