package com.dmitriyevseyev.carmanager2.server.network.handlers;

import com.dmitriyevseyev.carmanager2.client.view.RefreshHelper;
import com.dmitriyevseyev.carmanager2.server.controller.Controller;
import com.dmitriyevseyev.carmanager2.server.exceptions.AddCarExeption;
import com.dmitriyevseyev.carmanager2.server.exceptions.GetAllCarExeption;
import com.dmitriyevseyev.carmanager2.server.network.ServerSendler;
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
            Controller.getInstance().addCar(car);
        } catch (AddCarExeption e) {
            Command error = new Command(CommandId.ERROR, "error AddCar,  " + e.getMessage());
            ServerSendler.getInstance().send(error);
            System.out.println(e.getMessage());
        }
        try {
            Command com = new Command(CommandId.GET_ALL_CARS, Controller.getInstance().getAllCars());
            System.out.println("Com responce (GetAllCarsHandler/AddCarHandler) - " + com + "\n");
            ServerSendler.getInstance().send(com);
        } catch (GetAllCarExeption e) {
            Command error = new Command(CommandId.ERROR, "error GetAllCars,  " + e.getMessage());
            ServerSendler.getInstance().send(error);
            System.out.println(e.getMessage());
        }
    }
}
