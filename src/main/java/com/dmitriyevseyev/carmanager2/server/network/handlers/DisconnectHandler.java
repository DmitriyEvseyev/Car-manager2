package com.dmitriyevseyev.carmanager2.server.network.handlers;

import com.dmitriyevseyev.carmanager2.server.network.SevserFasade;
import com.dmitriyevseyev.carmanager2.shared.Command;
import com.dmitriyevseyev.carmanager2.shared.CommandId;

public class DisconnectHandler implements HandlerServer{
    @Override
    public void handle(Command command) {
        Command com = new Command(CommandId.DISCONNECT, "");
        SevserFasade.getInstance().sendler(com);
        SevserFasade.getInstance().setExit(false);
    }
}
