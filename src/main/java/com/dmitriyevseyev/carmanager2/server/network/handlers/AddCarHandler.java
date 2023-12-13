package com.dmitriyevseyev.carmanager2.server.network.handlers;

import com.dmitriyevseyev.carmanager2.server.controller.Controller;
import com.dmitriyevseyev.carmanager2.server.exceptions.AddCarExeption;
import com.dmitriyevseyev.carmanager2.server.network.SevserFasade;
import com.dmitriyevseyev.carmanager2.shared.Car;
import com.dmitriyevseyev.carmanager2.shared.Command;
import com.dmitriyevseyev.carmanager2.shared.CommandId;

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
            Command error = new Command(CommandId.ERROR,"error AddCar,  " + e.getMessage());
            SevserFasade.getInstance().sendler(error);
            System.out.println(e.getMessage());
        }
    }
}
