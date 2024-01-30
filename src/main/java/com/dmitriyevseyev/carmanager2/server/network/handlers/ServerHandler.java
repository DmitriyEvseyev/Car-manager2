package com.dmitriyevseyev.carmanager2.server.network.handlers;

import com.dmitriyevseyev.carmanager2.shared.model.Command;

public interface ServerHandler {
    void  handle (Command command);
}
