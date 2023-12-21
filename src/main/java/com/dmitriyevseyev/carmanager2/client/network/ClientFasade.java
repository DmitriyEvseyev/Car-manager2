
package com.dmitriyevseyev.carmanager2.client.network;

import com.dmitriyevseyev.carmanager2.client.controller.ControllerClient;
import com.dmitriyevseyev.carmanager2.client.view.CLIView;
import com.dmitriyevseyev.carmanager2.client.view.CarFx;
import com.dmitriyevseyev.carmanager2.client.view.Converter;
import com.dmitriyevseyev.carmanager2.client.view.RefreshHelper;
import com.dmitriyevseyev.carmanager2.shared.Car;
import com.dmitriyevseyev.carmanager2.shared.Command;
import com.dmitriyevseyev.carmanager2.shared.CommandId;

import java.io.*;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import static com.dmitriyevseyev.carmanager2.shared.Constants.SERVER_PORT;
import static com.dmitriyevseyev.carmanager2.shared.Constants.SERVER_URL;

public class ClientFasade {
    private static ClientFasade instance;
    private Socket socket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private ObjectInputStream objectInputStr;


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
            this.objectInputStr = new ObjectInputStream(socket.getInputStream());

            // while (true) {
            SendlerClient sendlerCl = SendlerClient.getInstance();
            sendlerCl.setObjectOutputStream(objectOutputStream);
            sendlerCl.send(new Command(CommandId.GET_ALL_CARS, ""));

            // ControllerClient.getInstance().getAllCars();

            ListenerClient listenerClient = new ListenerClient();
            listenerClient.setObjectInputStream(objectInputStream);

            try {
                Command responce = (Command) objectInputStream.readObject();
                List<Car> carL = (List<Car>) responce.getData();
                HashMap<Integer, Car> map = new HashMap<>();
                for (Car car : carL) {
                    map.put(car.getId(), car);
                }
                CarMap.getInstance().setCarMap(map);

                System.out.println("CARMAP fasade - " + CarMap.getInstance().getCarMap());

                CLIView.run();

               // RefreshHelper.getInstance().getControllerView().refresh();

            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }

            //ControllerClient.getInstance().removeCar(20);
            // sendlerClient.send(new Command(CommandId.GET_ALL_CARS, ""));

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

                /*try {
                    Command resp = (Command) objectInputStream.readObject();
                    List<Car> carL = CommandManagerClient.getInstance().processCommand(resp);

                    for (Car car : carL) {
                        System.out.println(car);
                    }

                } catch (ClassNotFoundException e) {
                    System.out.println(e.getMessage());
                }

                 */
            // }
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