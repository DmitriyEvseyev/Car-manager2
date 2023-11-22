package com.dmitriyevseyev.carmanager2.client.view;

import java.util.ArrayList;
import java.util.List;

import com.dmitriyevseyev.carmanager2.client.controller.ControllerClient;
import com.dmitriyevseyev.carmanager2.shared.Car;

public class Converter {
    private static Converter instance;
    private ControllerClient controllerClient;

    public static Converter getInstance() {
        if (instance == null) {
            instance = new Converter();
        }
        return instance;
    }

    private Converter() {
        this.controllerClient = ControllerClient.getInstance();
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
        return CarFx.builder()
                .id(car.getId())
                .name(car.getName())
                .date(car.getDate())
                .color(car.getColor())
                .isAfterCrash(car.isAfterCrash())
                .build();
    }

    public Car convertCarFxToCar(CarFx carFx) {
        return Car.builder()
                .id(carFx.getId())
                .name(carFx.getName())
                .date(carFx.getDate())
                .color(carFx.getColor())
                .isAfterCrash(carFx.isIsAfterCrash())
                .build();
    }
}
