package com.dmitriyevseyev.carmanager2.server.network.handlers;

import com.dmitriyevseyev.carmanager2.server.exceptions.carExceptions.AddCarExeption;
import com.dmitriyevseyev.carmanager2.server.exceptions.carExceptions.GetAllCarExeption;
import com.dmitriyevseyev.carmanager2.server.network.ServerSendler;
import com.dmitriyevseyev.carmanager2.server.serverController.ServerCarController;
import com.dmitriyevseyev.carmanager2.server.serverController.ServerUserController;
import com.dmitriyevseyev.carmanager2.shared.Command;
import com.dmitriyevseyev.carmanager2.shared.CommandId;
import com.dmitriyevseyev.carmanager2.shared.User;

public class NewUserHandler implements HandlerServer {
    @Override
    public void handle(Command command) {

        System.out.println("new ");
        User newUser = (User) command.getData();
        try {

            ServerUserController.getInstance().addUser(newUser);


        } catch (AddCarExeption e) {
            Command error = new Command(CommandId.ERROR, "error NewUser,  " + e.getMessage());
            ServerSendler.getInstance().send(error);
            System.out.println(e.getMessage());
        }
        try {
            Command com = new Command(CommandId.GET_ALL_CARS, ServerCarController.getInstance().getAllCars());
            System.out.println("Com responce (GetAllCarsHandler/NewUserHandler) - " + com + "\n");
            ServerSendler.getInstance().send(com);
        } catch (GetAllCarExeption e) {
            Command error = new Command(CommandId.ERROR, "error NewUserHandler,  " + e.getMessage());
            ServerSendler.getInstance().send(error);
            System.out.println(e.getMessage());
        }
    }
}
