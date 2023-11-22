package com.dmitriyevseyev.carmanager2.client.network;

import com.dmitriyevseyev.carmanager2.client.network.handlers.GetAllCarsHandlerClient;
import com.dmitriyevseyev.carmanager2.server.network.handlers.GetAllCarsHandler;
import com.dmitriyevseyev.carmanager2.client.network.handlers.Handler;
import com.dmitriyevseyev.carmanager2.shared.Car;
import com.dmitriyevseyev.carmanager2.shared.Command;
import com.dmitriyevseyev.carmanager2.shared.CommandId;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class CommandManagerClient {
    private static CommandManagerClient instance;
    private Socket clientSocket;

    public static CommandManagerClient getInstance(Socket clientSocket) {
        if (instance == null) {
            instance = new CommandManagerClient(clientSocket);
        }
        return instance;
    }

    private Map<Integer, Handler> handlerMap;

    private CommandManagerClient(Socket client) {
        this.clientSocket = client;
        handlerMap = new HashMap<>();
        handlerMap.put(CommandId.GET_ALL_CARS, new GetAllCarsHandlerClient());
        /* handlerMap.put(ServerCommandIdConstants.ADD_TASK, new AddTaskHandler());
        handlerMap.put(ServerCommandIdConstants.DELETE_TASK, new DeleteTaskHandler());
        handlerMap.put(ServerCommandIdConstants.CHANGE_TASK, new ChangeTaskHandler());

        handlerMap.put(ServerCommandIdConstants.DISCONNECT, new DisconnectHandler());
        handlerMap.put(ServerCommandIdConstants.ALL_DISCONNECT, new AllDisconnectHandler());
        */
    }

    public void processCommand(Command command) {
        HashMap<Integer, Car> carMap = new HashMap<>(handlerMap.get(command.getAction()).handle(command));

            System.out.println("responce from server - " + carMap);
    }
}

