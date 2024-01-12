package com.dmitriyevseyev.carmanager2.server.network.handlers;

import com.dmitriyevseyev.carmanager2.server.serverController.ServerController;
import com.dmitriyevseyev.carmanager2.server.exceptions.DeleteCarExeption;
import com.dmitriyevseyev.carmanager2.server.exceptions.GetAllCarExeption;
import com.dmitriyevseyev.carmanager2.server.exceptions.NotFoundException;
import com.dmitriyevseyev.carmanager2.server.network.ServerSendler;
import com.dmitriyevseyev.carmanager2.shared.Command;
import com.dmitriyevseyev.carmanager2.shared.CommandId;

public class DeleteCarHandler implements HandlerServer {
    @Override
    public void handle(Command command) {

        Integer id = (Integer) command.getData();
        System.out.println("It will be deleted ID - " + id);
        try {
            ServerController.getInstance().removeCar(id);
        } catch (NotFoundException e) {
            System.out.println("There is no this car. " + e.getMessage());
        } catch (DeleteCarExeption e) {
            Command error = new Command(CommandId.ERROR,"error DeleteCar,  " + e.getMessage());
            ServerSendler.getInstance().send(error);
            System.out.println(e.getMessage());
        }

        try {
            Command com = new Command(CommandId.GET_ALL_CARS, ServerController.getInstance().getAllCars());
            System.out.println("Com responce (GetAllCarsHandler/DeleteCarHandler) - " + com + "\n");
            ServerSendler.getInstance().send(com);
        } catch (GetAllCarExeption e) {
            Command error = new Command(CommandId.ERROR, "error GetAllCars,  " + e.getMessage());
            ServerSendler.getInstance().send(error);
            System.out.println(e.getMessage());
        }

    }
}
