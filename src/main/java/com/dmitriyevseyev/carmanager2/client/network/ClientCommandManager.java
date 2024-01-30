package com.dmitriyevseyev.carmanager2.client.network;

import com.dmitriyevseyev.carmanager2.client.network.handlers.*;
import com.dmitriyevseyev.carmanager2.shared.model.Command;
import com.dmitriyevseyev.carmanager2.shared.model.CommandId;

import java.util.HashMap;
import java.util.Map;

public class ClientCommandManager {
    private static ClientCommandManager instance;

    public static ClientCommandManager getInstance() {
        if (instance == null) {
            instance = new ClientCommandManager();
        }
        return instance;
    }

    private Map<Integer, ClientHandler> handlerMap;

    private ClientCommandManager() {
        handlerMap = new HashMap<>();
        handlerMap.put(CommandId.GET_ALL_CARS, new GetAllCarsClientHandler());
        handlerMap.put(CommandId.ERROR, new ErrorClientHandler());
        handlerMap.put(CommandId.DISCONNECT, new DisconnectClientHandler());
        handlerMap.put(CommandId.REGISTER_NEW_USER, new RegisterNewUserClientHandler());
        handlerMap.put(CommandId.GET_USER_ID, new IdUserClientHandler());

        //handlerMap.put(ServerCommandIdConstants.ALL_DISCONNECT, new AllDisconnectHandler());
    }

    public void processCommand(Command command) {
        handlerMap.get(command.getAction()).handle(command);

    }
}

