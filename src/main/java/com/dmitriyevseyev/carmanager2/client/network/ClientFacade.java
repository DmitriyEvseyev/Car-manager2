
package com.dmitriyevseyev.carmanager2.client.network;

import com.dmitriyevseyev.carmanager2.shared.Car;
import com.dmitriyevseyev.carmanager2.shared.Command;
import com.dmitriyevseyev.carmanager2.shared.CommandId;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static com.dmitriyevseyev.carmanager2.shared.Constants.SERVER_PORT;
import static com.dmitriyevseyev.carmanager2.shared.Constants.SERVER_URL;

public class ClientFacade {
    private static ClientFacade instance;
    private Socket socket;

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

            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                 ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream())) {

                Command command = new Command(CommandId.GET_ALL_CARS, "");
                System.out.println(command);
                objectOutputStream.writeObject(command);

                try {
                    Command resp = (Command) objectInputStream.readObject();
                    System.out.println("resp ---- " + resp.getClass());
                    List<Car> carL = (List<Car>) resp.getData();

                    for (Car car : carL) {
                        System.out.println(car);
                    }

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}