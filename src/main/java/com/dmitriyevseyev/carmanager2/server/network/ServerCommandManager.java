package com.dmitriyevseyev.carmanager2.server.network;

import com.dmitriyevseyev.carmanager2.server.network.handlers.*;

import com.dmitriyevseyev.carmanager2.shared.Command;
import com.dmitriyevseyev.carmanager2.shared.CommandId;

import java.util.HashMap;
import java.util.Map;

public class ServerCommandManager {
    private static ServerCommandManager instance;

    public static ServerCommandManager getInstance() {
        if (instance == null) {
            instance = new ServerCommandManager();
        }
        return instance;
    }

    private Map<Integer, HandlerServer> handlerMap;

    private ServerCommandManager() {
        handlerMap = new HashMap<>();
        handlerMap.put(CommandId.GET_ALL_CARS, new GetAllCarsHandler());
        handlerMap.put(CommandId.ADD_CAR, new AddCarHandler());
        handlerMap.put(CommandId.DELETE_CAR, new DeleteCarHandler());
        handlerMap.put(CommandId.UPDATE_CAR, new UpdateCarHandler());
        handlerMap.put(CommandId.DISCONNECT, new DisconnectHandler());
        handlerMap.put(CommandId.AUTHORIZATION, new AuthorizationHandler());
        handlerMap.put(CommandId.NEW_USER, new NewUserHandler());
    }

    public void processCommand(Command command) {
        handlerMap.get(command.getAction()).handle(command);
    }
}

