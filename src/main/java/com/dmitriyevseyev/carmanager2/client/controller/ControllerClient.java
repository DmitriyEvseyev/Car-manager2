package com.dmitriyevseyev.carmanager2.client.controller;

import com.dmitriyevseyev.carmanager2.shared.Car;

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

    public List<Car> getAllCars() {
        ArrayList<Car> arrCar = new ArrayList<>(mapCar.values());
        return Collections.unmodifiableList(arrCar);
    }

    public void addCar(String name, Date date, String color, boolean isAfterCrash) {
        Integer idRandom = new Random().nextInt(100 + 1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
        Car car = null;

            car = Car.builder().
                    id(idRandom).
                    name(name).
                    date(date).
                    color(color).
                    isAfterCrash(isAfterCrash).
                    build();

        mapCar.put(car.getId(), car);
    }

    public void removeCar(Integer id) {
        mapCar.remove(id);
    }

    public void updateCar(Car car) {
        Car editCar = mapCar.get(car.getId());

        mapCar.remove(car.getId());

        editCar.setName(car.getName());
        editCar.setDate(car.getDate());
        editCar.setColor(car.getColor());
        editCar.setAfterCrash(car.isAfterCrash());

        mapCar.put(editCar.getId(), editCar);
    }
}

