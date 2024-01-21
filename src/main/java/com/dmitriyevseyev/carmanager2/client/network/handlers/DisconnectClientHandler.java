package com.dmitriyevseyev.carmanager2.client.network.handlers;

import com.dmitriyevseyev.carmanager2.client.network.ClientListener;
import com.dmitriyevseyev.carmanager2.client.network.ClientSendler;
import com.dmitriyevseyev.carmanager2.shared.model.Command;

public class DisconnectClientHandler implements ClientHandler {
    @Override
    public void handle(Command command) {
        System.out.println(command);
        ClientListener.getInstance().setExit(false);
        ClientSendler.getInstance().close();
    }
}
