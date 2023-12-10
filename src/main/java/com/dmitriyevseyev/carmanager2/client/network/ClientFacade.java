
package com.dmitriyevseyev.carmanager2.client.network;

import com.dmitriyevseyev.carmanager2.shared.Car;
import com.dmitriyevseyev.carmanager2.shared.Command;
import com.dmitriyevseyev.carmanager2.shared.CommandId;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.*;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import static com.dmitriyevseyev.carmanager2.shared.Constants.SERVER_PORT;
import static com.dmitriyevseyev.carmanager2.shared.Constants.SERVER_URL;

public class ClientFacade {
    private static ClientFacade instance;
    private Socket socket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;


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

            this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            this.objectInputStream = new ObjectInputStream(socket.getInputStream());

            SendlerClient sendlerClient = SendlerClient.getInstance();
            sendlerClient.setObjectOutputStream(objectOutputStream);
            sendlerClient.send(new Command(CommandId.GET_ALL_CARS, ""));

            //Command command = new Command(CommandId.GET_ALL_CARS, "");

            //Command command = new Command(CommandId.DELETE_CAR, "19");

           /* Integer idRandom = new Random().nextInt(100 + 1);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
            Car carNew = null;
            try {
                carNew = Car.builder().
                        id(idRandom).
                        name("carNew").
                        date(formatter.parse("2000-01-01")).
                        color("color").
                        isAfterCrash(false).
                        build();
            } catch (ParseException e) {
                System.out.println(e.getMessage());
            }
            Command command = new Command(CommandId.ADD_CAR, carNew);

            */

            //System.out.println(command);
            //objectOutputStream.writeObject(command);

            try {
                Command resp = (Command) objectInputStream.readObject();
                List<Car> carL = CommandManagerClient.getInstance().processCommand(resp);

                for (Car car : carL) {
                    System.out.println(car);
                }

            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
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