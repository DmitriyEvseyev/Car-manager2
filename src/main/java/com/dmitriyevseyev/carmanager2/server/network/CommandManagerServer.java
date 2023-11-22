package com.dmitriyevseyev.carmanager2.server.network;

import com.dmitriyevseyev.carmanager2.server.network.handlers.GetAllCarsHandler;
import com.dmitriyevseyev.carmanager2.server.network.handlers.Handler;
import com.dmitriyevseyev.carmanager2.shared.Command;
import com.dmitriyevseyev.carmanager2.shared.CommandId;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class CommandManagerServer {
    private static CommandManagerServer instance;
    private Socket clientSocket;

    public static CommandManagerServer getInstance(Socket clientSocket) {
        if (instance == null) {
            instance = new CommandManagerServer(clientSocket);
        }
        return instance;
    }

    private Map<Integer, Handler> handlerMap;

    private CommandManagerServer(Socket client) {
        this.clientSocket = client;
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
        try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {
            String answer = handlerMap.get(command.getAction()).handle();
            System.out.println("answer server - " + answer);

            out.write(answer + "\n");
            out.flush();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

