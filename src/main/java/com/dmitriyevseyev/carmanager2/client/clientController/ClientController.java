package com.dmitriyevseyev.carmanager2.client.clientController;

import com.dmitriyevseyev.carmanager2.client.network.ClientSendler;
import com.dmitriyevseyev.carmanager2.shared.Car;
import com.dmitriyevseyev.carmanager2.shared.Command;
import com.dmitriyevseyev.carmanager2.shared.CommandId;

import java.util.*;

public class ClientController {
    private static ClientController instance;
    private List <Car> carList;

    public static ClientController getInstance() {
        if (instance == null) {
            instance = new ClientController();
        }
        return instance;
    }

    private ClientController() {
        this.carList = new ArrayList<>();
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }

    public List<Car> getCarList() {
        return carList;
    }

    public void addCar(String name, Date date, String color, boolean isAfterCrash) {
        Car car = Car.builder().
                name(name).
                date(date).
                color(color).
                isAfterCrash(isAfterCrash).
                build();
        Command command = new Command(CommandId.ADD_CAR, car);
        ClientSendler.getInstance().send(command);
    }

    public void removeCar(Integer id) {
        Command command = new Command(CommandId.DELETE_CAR, id);
        ClientSendler.getInstance().send(command);
    }

    public void updateCar(Car car) {
        Command command = new Command(CommandId.UPDATE_CAR, car);
        ClientSendler.getInstance().send(command);
    }
}

