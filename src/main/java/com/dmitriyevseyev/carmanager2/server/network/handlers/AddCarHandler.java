package com.dmitriyevseyev.carmanager2.server.network.handlers;

import com.dmitriyevseyev.carmanager2.server.controller.Controller;
import com.dmitriyevseyev.carmanager2.server.exceptions.AddCarExeption;
import com.dmitriyevseyev.carmanager2.shared.Car;
import com.dmitriyevseyev.carmanager2.shared.Command;

public class AddCarHandler implements HandlerServer {
    @Override
    public void handle(Command command) {
        Car car = (Car) command.getData();
        System.out.println("new Car - " + car);

        try {
            Controller.getInstance().addCar(
                    car.getName(),
                    car.getDate(),
                    car.getColor(),
                    car.isAfterCrash());
        } catch (AddCarExeption e) {
            System.out.println(e.getMessage());
        }
    }
}
