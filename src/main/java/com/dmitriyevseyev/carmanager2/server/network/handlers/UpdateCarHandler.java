package com.dmitriyevseyev.carmanager2.server.network.handlers;

import com.dmitriyevseyev.carmanager2.server.serverController.ServerController;
import com.dmitriyevseyev.carmanager2.server.exceptions.GetAllCarExeption;
import com.dmitriyevseyev.carmanager2.server.exceptions.UpdateCarException;
import com.dmitriyevseyev.carmanager2.server.network.ServerSendler;
import com.dmitriyevseyev.carmanager2.shared.Car;
import com.dmitriyevseyev.carmanager2.shared.Command;
import com.dmitriyevseyev.carmanager2.shared.CommandId;

public class UpdateCarHandler implements HandlerServer {
    @Override
    public void handle(Command command) {
        Car car = (Car) command.getData();
        System.out.println("updateCar - " + car);

        try {
            ServerController.getInstance().updateCar(car);
        } catch (UpdateCarException e) {
            Command error = new Command(CommandId.ERROR, "error UpdateCarException,  " + e.getMessage());
            ServerSendler.getInstance().send(error);
            System.out.println(e.getMessage());
        }

        try {
            Command com = new Command(CommandId.GET_ALL_CARS, ServerController.getInstance().getAllCars());
            System.out.println("Com responce (GetAllCarsHandler/ UpdateCarHandler) - " + com + "\n");
            ServerSendler.getInstance().send(com);

        } catch (GetAllCarExeption e) {
            Command error = new Command(CommandId.ERROR,"error GetAllCars,  " + e.getMessage());
            ServerSendler.getInstance().send(error);
            System.out.println(e.getMessage());
        }
    }
}
