package com.dmitriyevseyev.carmanager2.server.network;

import com.dmitriyevseyev.carmanager2.server.network.handlers.GetAllCarsHandler;

import com.dmitriyevseyev.carmanager2.server.network.handlers.HandlerServer;
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
        /* handlerMap.put(ServerCommandIdConstants.ADD_TASK, new AddTaskHandler());
        handlerMap.put(ServerCommandIdConstants.DELETE_TASK, new DeleteTaskHandler());
        handlerMap.put(ServerCommandIdConstants.CHANGE_TASK, new ChangeTaskHandler());
        handlerMap.put(ServerCommandIdConstants.CANCEL_TASK, new CancelTaskHandler());
        handlerMap.put(ServerCommandIdConstants.DISCONNECT, new DisconnectHandler());
        handlerMap.put(ServerCommandIdConstants.ALL_DISCONNECT, new AllDisconnectHandler());
        handlerMap.put(ServerCommandIdConstants.FINISH_TASK, new FinishTaskHandler());
        */
    }

    public void processCommand(Command command) {
        handlerMap.get(command.getAction()).handle(command);
    }
}

