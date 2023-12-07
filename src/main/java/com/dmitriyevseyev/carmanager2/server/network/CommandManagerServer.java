package com.dmitriyevseyev.carmanager2.server.network;

import com.dmitriyevseyev.carmanager2.server.network.handlers.*;

import com.dmitriyevseyev.carmanager2.shared.Command;
import com.dmitriyevseyev.carmanager2.shared.CommandId;

import java.util.HashMap;
import java.util.Map;

public class CommandManagerServer {
    private static CommandManagerServer instance;

    public static CommandManagerServer getInstance() {
        if (instance == null) {
            instance = new CommandManagerServer();
        }
        return instance;
    }

    private Map<Integer, HandlerServer> handlerMap;

    private CommandManagerServer() {
        handlerMap = new HashMap<>();
        handlerMap.put(CommandId.GET_ALL_CARS, new GetAllCarsHandler());
        handlerMap.put(CommandId.ADD_CAR, new AddCarHandler());
        handlerMap.put(CommandId.DELETE_CAR, new DeleteCarHandler());
        handlerMap.put(CommandId.UPDATE_CAR, new UpdateCarHandler());
    }

    public void processCommand(Command command) {
        handlerMap.get(command.getAction()).handle(command);
    }
}

