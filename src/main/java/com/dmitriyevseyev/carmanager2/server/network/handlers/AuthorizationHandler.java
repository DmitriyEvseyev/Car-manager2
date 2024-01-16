package com.dmitriyevseyev.carmanager2.server.network.handlers;

import com.dmitriyevseyev.carmanager2.server.exceptions.carExceptions.GetAllCarExeption;
import com.dmitriyevseyev.carmanager2.server.network.ServerSendler;
import com.dmitriyevseyev.carmanager2.server.serverController.ServerCarController;
import com.dmitriyevseyev.carmanager2.server.serverController.ServerUserController;
import com.dmitriyevseyev.carmanager2.shared.Command;
import com.dmitriyevseyev.carmanager2.shared.CommandId;
import com.dmitriyevseyev.carmanager2.shared.User;

public class AuthorizationHandler implements HandlerServer {
    @Override
    public void handle(Command command) {
        User user = (User) command.getData();
        System.out.println("User from client - " + user);
        System.out.println("111");

        boolean isCorrect = ServerUserController.getInstance().isUserExistServer(user);

        System.out.println(ServerUserController.getInstance().isUserExistServer(user));
        System.out.println("boolean - " + isCorrect);

        if (isCorrect) {
            try {
                Command com = new Command(CommandId.GET_ALL_CARS, ServerCarController.getInstance().getAllCars());
                System.out.println("Com responce (Authorization, GetAllCarsHandler) - " + com + "\n");
                ServerSendler.getInstance().send(com);

            } catch (GetAllCarExeption e) {
                Command error = new Command(CommandId.ERROR, "error GetAllCars,  " + e.getMessage());
                ServerSendler.getInstance().send(error);
                System.out.println(e.getMessage());
            }
        } else {
            Command comUser = new Command(CommandId.REGISTER_NEW_USER, "");
            System.out.println("New user. " + comUser);
            ServerSendler.getInstance().send(comUser);
        }
    }
}
