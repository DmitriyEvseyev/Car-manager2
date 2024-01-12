package com.dmitriyevseyev.carmanager2.server.network.handlers;

import com.dmitriyevseyev.carmanager2.server.serverController.ServerController;
import com.dmitriyevseyev.carmanager2.server.exceptions.GetAllCarExeption;
import com.dmitriyevseyev.carmanager2.server.network.ServerSendler;
import com.dmitriyevseyev.carmanager2.shared.Command;
import com.dmitriyevseyev.carmanager2.shared.CommandId;


public class GetAllCarsHandler implements HandlerServer {

    @Override
    public void handle(Command command) {

        try {
            Command com = new Command(command.getAction(), ServerController.getInstance().getAllCars());
            System.out.println("Com responce (GetAllCarsHandler) - " + com + "\n");
            ServerSendler.getInstance().send(com);

        } catch (GetAllCarExeption e) {
            Command error = new Command(CommandId.ERROR,"error GetAllCars,  " + e.getMessage());
            ServerSendler.getInstance().send(error);
            System.out.println(e.getMessage());
        }
    }
}


