package com.dmitriyevseyev.carmanager2.server.network;

import com.dmitriyevseyev.carmanager2.shared.Command;
import com.dmitriyevseyev.carmanager2.shared.CommandId;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static com.dmitriyevseyev.carmanager2.shared.Constants.SERVER_PORT;

public class SevserFasade {
    private static SevserFasade instance;
    private static Socket clientSocket;
    private static ServerSocket server;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private boolean exit;

    public static SevserFasade getInstance() {
        if (instance == null) {
            instance = new SevserFasade();
        }
        return instance;
    }

    private SevserFasade() {
    }

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    public void connect() {
        exit = true;

        try {
            server = new ServerSocket(Integer.parseInt(SERVER_PORT));
            System.out.println("The server is running!");
            clientSocket = server.accept();
            this.objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            this.objectInputStream = new ObjectInputStream(clientSocket.getInputStream());

            CommandManagerServer.getInstance().processCommand(new Command(CommandId.GET_ALL_CARS, ""));

        } catch (IOException e) {
            System.out.println("ConnectionServerError " + e.getMessage());
        }

        Command request = null;
        while (exit) {
            try {
                request = (Command) objectInputStream.readObject();
                System.out.println("request - " + request);
                CommandManagerServer.getInstance().processCommand(request);

            } catch (IOException e) {
                System.out.println("ReadObjectServerError " + e.getMessage());
            } catch (ClassNotFoundException ex) {
                System.out.println("ClassNotFoundExceptionServerError " + ex.getMessage());
            }
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