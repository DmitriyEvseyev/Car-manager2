package com.dmitriyevseyev.carmanager2.client.network.handlers;

import com.dmitriyevseyev.carmanager2.client.network.ClientListener;
import com.dmitriyevseyev.carmanager2.client.network.ClientSendler;
import com.dmitriyevseyev.carmanager2.shared.Command;

public class DisconnectHandlerClient implements HandlerClient {
    @Override
    public void handle(Command command) {
        System.out.println(command);
        ClientListener.getInstance().setExit(false);
        ClientSendler.getInstance().close();
    }
}
