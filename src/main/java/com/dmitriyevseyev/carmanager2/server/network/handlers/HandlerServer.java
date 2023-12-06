package com.dmitriyevseyev.carmanager2.server.network.handlers;

import com.dmitriyevseyev.carmanager2.shared.Command;

public interface HandlerServer {
    public void  handle (Command command);
}
