
package com.dmitriyevseyev.carmanager2.client.network;

import com.dmitriyevseyev.carmanager2.shared.Car;
import com.dmitriyevseyev.carmanager2.shared.Command;
import com.dmitriyevseyev.carmanager2.shared.CommandId;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;

import static com.dmitriyevseyev.carmanager2.shared.Constants.SERVER_PORT;
import static com.dmitriyevseyev.carmanager2.shared.Constants.SERVER_URL;

public class ClientFasade {
    private static ClientFasade instance;
    private Socket socket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public static ClientFasade getInstance() {
        if (instance == null) {
            instance = new ClientFasade();
        }
        return instance;
    }

    private ClientFasade() {
    }

    public void connect() {
        try {
            this.socket = new Socket(SERVER_URL, Integer.parseInt(SERVER_PORT));
            System.out.println("The client is running!");

            this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            this.objectInputStream = new ObjectInputStream(socket.getInputStream());

            SendlerClient sendlerCl = SendlerClient.getInstance();
            sendlerCl.setObjectOutputStream(objectOutputStream);
            sendlerCl.send(new Command(CommandId.GET_ALL_CARS, ""));

            ListenerClient listenerClient = ListenerClient.getInstance();
            listenerClient.setObjectInputStream(objectInputStream);

            try {
                Command responce = (Command) objectInputStream.readObject();
                List<Car> carL = (List<Car>) responce.getData();
                HashMap<Integer, Car> map = new HashMap<>();
                for (Car car : carL) {
                    map.put(car.getId(), car);
                }
                CarMap.getInstance().setCarMap(map);

                System.out.println("CARMAP FASADE - " + CarMap.getInstance().getCarMap());

            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
