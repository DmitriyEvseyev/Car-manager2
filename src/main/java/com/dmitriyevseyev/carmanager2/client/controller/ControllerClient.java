package com.dmitriyevseyev.carmanager2.client.controller;

import com.dmitriyevseyev.carmanager2.client.network.ListenerClient;
import com.dmitriyevseyev.carmanager2.client.network.SendlerClient;
import com.dmitriyevseyev.carmanager2.client.view.ControllerView;
import com.dmitriyevseyev.carmanager2.client.view.RefreshHelper;
import com.dmitriyevseyev.carmanager2.shared.Car;
import com.dmitriyevseyev.carmanager2.shared.Command;
import com.dmitriyevseyev.carmanager2.shared.CommandId;

import java.util.*;

public class ControllerClient {
    private static ControllerClient instance;

    public static ControllerClient getInstance() {
        if (instance == null) {
            instance = new ControllerClient();
        }
        return instance;
    }

    private ControllerClient() {
    }

    public void addCar(String name, Date date, String color, boolean isAfterCrash) {
        Car car = Car.builder().
                name(name).
                date(date).
                color(color).
                isAfterCrash(isAfterCrash).
                build();
        Command command = new Command(CommandId.ADD_CAR, car);
        SendlerClient.getInstance().send(command);
    }

    public void removeCar(Integer id) {
        Command command = new Command(CommandId.DELETE_CAR, id);
        SendlerClient.getInstance().send(command);
    }

    public void updateCar(Car car) {
        Command command = new Command(CommandId.UPDATE_CAR, car);
        SendlerClient.getInstance().send(command);
    }
}

