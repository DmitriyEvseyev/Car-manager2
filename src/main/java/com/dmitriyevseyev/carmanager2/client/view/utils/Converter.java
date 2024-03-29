package com.dmitriyevseyev.carmanager2.client.view.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.dmitriyevseyev.carmanager2.client.controller.ClientController;
import com.dmitriyevseyev.carmanager2.client.view.model.CarFx;
import com.dmitriyevseyev.carmanager2.shared.model.Car;

public class Converter {
    private static Converter instance;
    private ClientController controllerClient;

    public static Converter getInstance() {
        if (instance == null) {
            instance = new Converter();
        }
        return instance;
    }

    private Converter() {
        this.controllerClient = ClientController.getInstance();
    }

    public List<CarFx> convertCarListToCarFxList(List<Car> cars) {
        List<CarFx> list = new ArrayList<>();

        for (Car car : cars) {
            CarFx carFx = convertCarToCarFx(car);
            list.add(carFx);
        }
        return list;
    }

    public CarFx convertCarToCarFx(Car car) {
          CarFx carFx = new CarFx(car.getId(),
                car.getName(),
                car.getDate().toString(),
                car.getColor(),
                car.isAfterCrash());
        return carFx;
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
            System.out.println(e.getMessage());
        }
        return car;
    }
}
