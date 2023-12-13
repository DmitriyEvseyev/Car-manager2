package com.dmitriyevseyev.carmanager2.server.network.handlers;

import com.dmitriyevseyev.carmanager2.server.controller.Controller;
import com.dmitriyevseyev.carmanager2.server.exceptions.AddCarExeption;
import com.dmitriyevseyev.carmanager2.server.exceptions.UpdateCarException;
import com.dmitriyevseyev.carmanager2.shared.Car;
import com.dmitriyevseyev.carmanager2.shared.Command;

public class UpdateCarHandler implements HandlerServer {
    @Override
    public void handle(Command command) {
        Car car = (Car) command.getData();
        System.out.println("updateCar - " + car);

        try {
            Controller.getInstance().updateCar(car);
        } catch (UpdateCarException e) {

            System.out.println(e.getMessage());
        }
    }
}
