package com.dmitriyevseyev.carmanager2.server.network.handlers;

import com.dmitriyevseyev.carmanager2.server.controller.Controller;
import com.dmitriyevseyev.carmanager2.server.exceptions.AddCarExeption;
import com.dmitriyevseyev.carmanager2.server.exceptions.GetAllCarExeption;
import com.dmitriyevseyev.carmanager2.server.exceptions.UpdateCarException;
import com.dmitriyevseyev.carmanager2.server.network.SevserFasade;
import com.dmitriyevseyev.carmanager2.shared.Car;
import com.dmitriyevseyev.carmanager2.shared.Command;
import com.dmitriyevseyev.carmanager2.shared.CommandId;

public class UpdateCarHandler implements HandlerServer {
    @Override
    public void handle(Command command) {
        Car car = (Car) command.getData();
        System.out.println("updateCar - " + car);

        try {
            Controller.getInstance().updateCar(car);
        } catch (UpdateCarException e) {
            Command error = new Command(CommandId.ERROR, "error UpdateCarException,  " + e.getMessage());
            SevserFasade.getInstance().sendler(error);
            System.out.println(e.getMessage());
        }

        try {
            Command com = new Command(CommandId.GET_ALL_CARS, Controller.getInstance().getAllCars());
            System.out.println("Com responce (GetAllCarsHandler/ UpdateCarHandler) - " + com + "\n");
            SevserFasade.getInstance().sendler(com);

        } catch (GetAllCarExeption e) {
            Command error = new Command(CommandId.ERROR,"error GetAllCars,  " + e.getMessage());
            SevserFasade.getInstance().sendler(error);
            System.out.println(e.getMessage());
        }
    }
}
