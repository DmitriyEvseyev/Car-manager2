package com.dmitriyevseyev.carmanager2.server.network;

import com.dmitriyevseyev.carmanager2.shared.Command;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static com.dmitriyevseyev.carmanager2.shared.Constants.SERVER_PORT;
import static com.dmitriyevseyev.carmanager2.shared.Constants.SERVER_URL;

public class SevserFasade {
    private static SevserFasade instance;
    private static Socket clientSocket;
    private static ServerSocket server;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public static SevserFasade getInstance() {
        if (instance == null) {
            instance = new SevserFasade();
        }
        return instance;
    }

    private SevserFasade() {
    }

    public void connect() {
        try {
            server = new ServerSocket(Integer.parseInt(SERVER_PORT));
            System.out.println("The server is running!");
            clientSocket = server.accept();
            this.objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            this.objectInputStream = new ObjectInputStream(clientSocket.getInputStream());

            Command request = null;
            try {
                request = (Command) objectInputStream.readObject();
                System.out.println("request - " + request);
                CommandManagerServer.getInstance().processCommand(request);
            } catch (ClassNotFoundException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

   public void sendler(Command command) {

        try {
            this.objectOutputStream.writeObject(command);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}