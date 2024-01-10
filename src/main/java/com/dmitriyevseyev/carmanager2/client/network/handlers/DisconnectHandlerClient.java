package com.dmitriyevseyev.carmanager2.client.network.handlers;

import com.dmitriyevseyev.carmanager2.client.network.ClientFasade;
import com.dmitriyevseyev.carmanager2.client.network.ListenerClient;
import com.dmitriyevseyev.carmanager2.client.network.SendlerClient;
import com.dmitriyevseyev.carmanager2.shared.Command;

public class DisconnectHandlerClient implements HandlerClient {
    @Override
    public void handle(Command command) {
        System.out.println(command);
        ListenerClient.getInstance().setExit(false);

       // ClientFasade.getInstance().getListenerClient().isInterrupted();
        SendlerClient.getInstance().close();




    }
}
