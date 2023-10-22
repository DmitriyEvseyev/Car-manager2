package com.dmitriyevseyev.carmanager2.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import com.dmitriyevseyev.carmanager2.model.Car;
import com.dmitriyevseyev.carmanager2.exceptions.GetAllCarExeption;
import com.dmitriyevseyev.carmanager2.controller.Controller;


public class Converter {
    private static Converter instance;
    private Controller controller;

    public static Converter getInstance() {
        if (instance == null) {
            instance = new Converter();
        }
        return instance;
    }

    private Converter() {
        this.controller = Controller.getInstance();
    }

    public ArrayList convertCarToCarFx(ArrayList<CarFx> carFxConvertn) {
        try {
            for (int i = 0; i < controller.getAllCars().size(); i++) {

                carFxConvertn.add(CarFx.builder()
                        .id(controller.getAllCars().get(i).getId())
                        .name(controller.getAllCars().get(i).getName())
                        .date(controller.getAllCars().get(i).getDate().toString())
                        .color(controller.getAllCars().get(i).getColor())
                        .isAfterCrash(controller.getAllCars().get(i).isAfterCrash())
                        .build());
            }
        } catch (GetAllCarExeption e) {
            System.out.println(e.getMessage());
        }
        return carFxConvertn;
    }

    public Car convertCarFxToCar(CarFx carFx) {
        Car car = new Car();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
        try {
            car = Car.builder()
                    .id(carFx.getId())
                    .name(carFx.getName())
                    .date(formatter.parse(carFx.getDate()))
                    .color(carFx.getColor())
                    .isAfterCrash(carFx.isIsAfterCrash())
                    .build();
        } catch (ParseException e) {
            e.getMessage();
        }
        return car;
    }
}
