package com.dmitriyevseyev.carmanager2.client.network.handlers;

import com.dmitriyevseyev.carmanager2.client.controller.ClientController;
import com.dmitriyevseyev.carmanager2.shared.model.Command;

public class IdUserClientHandler implements ClientHandler {
    @Override
    public void handle(Command command) {
        Integer idUser = (Integer) command.getData();
        System.out.println("IdUserClient - " + idUser);
        ClientController.getInstance().setIdUser(idUser);
    }
}
