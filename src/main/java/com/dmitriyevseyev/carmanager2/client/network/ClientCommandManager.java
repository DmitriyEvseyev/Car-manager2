package com.dmitriyevseyev.carmanager2.client.network;

import com.dmitriyevseyev.carmanager2.client.network.handlers.DisconnectHandlerClient;
import com.dmitriyevseyev.carmanager2.client.network.handlers.ErrorHandlerClient;
import com.dmitriyevseyev.carmanager2.client.network.handlers.GetAllCarsHandlerClient;
import com.dmitriyevseyev.carmanager2.client.network.handlers.HandlerClient;
import com.dmitriyevseyev.carmanager2.shared.Command;
import com.dmitriyevseyev.carmanager2.shared.CommandId;

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

    private Map<Integer, HandlerClient> handlerMap;

    private ClientCommandManager() {
        handlerMap = new HashMap<>();
        handlerMap.put(CommandId.GET_ALL_CARS, new GetAllCarsHandlerClient());
        handlerMap.put(CommandId.ERROR, new ErrorHandlerClient());
        handlerMap.put(CommandId.DISCONNECT, new DisconnectHandlerClient());
        //handlerMap.put(ServerCommandIdConstants.ALL_DISCONNECT, new AllDisconnectHandler());
    }

    public void processCommand(Command command) {
        handlerMap.get(command.getAction()).handle(command);

    }
}

