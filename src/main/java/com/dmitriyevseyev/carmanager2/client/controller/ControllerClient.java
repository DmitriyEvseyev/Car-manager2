package com.dmitriyevseyev.carmanager2.client.controller;

import com.dmitriyevseyev.carmanager2.client.network.CarMap;
import com.dmitriyevseyev.carmanager2.client.network.SendlerClient;
import com.dmitriyevseyev.carmanager2.client.view.RefreshHelper;
import com.dmitriyevseyev.carmanager2.server.controller.Controller;
import com.dmitriyevseyev.carmanager2.server.exceptions.GetAllCarExeption;
import com.dmitriyevseyev.carmanager2.shared.Car;
import com.dmitriyevseyev.carmanager2.shared.Command;
import com.dmitriyevseyev.carmanager2.shared.CommandId;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ControllerClient {
    private static ControllerClient instance;
    private HashMap<Integer, Car> mapCar;

    public static ControllerClient getInstance() {
        if (instance == null) {
            instance = new ControllerClient();
        }
        return instance;
    }

    private ControllerClient() {
        this.mapCar = new HashMap<>();
    }

    public HashMap<Integer, Car> getMapCar() {
        return mapCar;
    }

    public void setMapCar(HashMap<Integer, Car> mapCar) {
        this.mapCar = mapCar;
    }

    public void getAllCars() {
        Command command = new Command(CommandId.GET_ALL_CARS, "");
        SendlerClient.getInstance().send(command);

       // ArrayList<Car> arrCar = new ArrayList<>(mapCar.values());
        // return Collections.unmodifiableList(arrCar);
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

        //ControllerClient.getInstance().getAllCars();

       Command commandRefresh = new Command(CommandId.GET_ALL_CARS, "");
       SendlerClient.getInstance().send(commandRefresh);

        System.out.println("CONTROL - " + CarMap.getInstance().getCarMap());
        // RefreshHelper.getInstance().getControllerView().refresh();
    }

    public void removeCar(Integer id) {
        Command command = new Command(CommandId.DELETE_CAR, id);
        SendlerClient.getInstance().send(command);
        RefreshHelper.getInstance().getControllerView().refresh();
    }

    public void updateCar(Car car) {
        Command command = new Command(CommandId.UPDATE_CAR, car);
        SendlerClient.getInstance().send(command);
    }
}

