package com.dmitriyevseyev.carmanager2.server.network.handlers;

import com.dmitriyevseyev.carmanager2.server.network.MonoClientThread;
import com.dmitriyevseyev.carmanager2.server.network.ServerFacade;
import com.dmitriyevseyev.carmanager2.shared.model.Command;
import com.dmitriyevseyev.carmanager2.shared.model.CommandId;

public class DisconnectServerHandler implements ServerHandler {
    @Override
    public void handle(Command command) {
        Integer idUser = (Integer) command.getData();
        MonoClientThread monoClient = ServerFacade.getInstance().getThreadHashMap().get(idUser);
        Command com = new Command(CommandId.DISCONNECT, "");

        monoClient.disconnectClient();
        monoClient.getServerSendler().send(com);

        ServerFacade.getInstance().getThreadHashMap().remove(idUser);
    }
}
