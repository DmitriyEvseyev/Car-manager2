package com.dmitriyevseyev.carmanager2.server.network.handlers;

import com.dmitriyevseyev.carmanager2.server.controller.ServerCarController;
import com.dmitriyevseyev.carmanager2.server.exceptions.car.GetAllCarExeption;
import com.dmitriyevseyev.carmanager2.server.network.MonoClientThread;
import com.dmitriyevseyev.carmanager2.server.network.ServerFacade;
import com.dmitriyevseyev.carmanager2.server.network.ServerSendler;
import com.dmitriyevseyev.carmanager2.shared.model.Command;
import com.dmitriyevseyev.carmanager2.shared.model.CommandId;


public class GetAllCarsServerHandler implements ServerHandler {

    @Override
    public void handle(Command command) {
        Integer idUser = (Integer) command.getData();
        MonoClientThread monoClientThread = ServerFacade.getInstance().getThreadHashMap().get(idUser);
        try {
            Command com = new Command(CommandId.GET_ALL_CARS, ServerCarController.getInstance().getAllCars());
            System.out.println("Com responce (GetAllCarsServerHandler) - " + com + "\n");
            monoClientThread.getServerSendler().send(com);


        } catch (GetAllCarExeption e) {
            Command error = new Command(CommandId.ERROR, "error GetAllCars,  " + e.getMessage());
            monoClientThread.getServerSendler().send(error);
            System.out.println(e.getMessage());
        }
    }
}


