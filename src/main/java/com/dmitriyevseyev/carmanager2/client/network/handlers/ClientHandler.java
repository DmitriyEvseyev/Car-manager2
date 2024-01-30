package com.dmitriyevseyev.carmanager2.client.network.handlers;

import com.dmitriyevseyev.carmanager2.shared.model.Command;

public interface ClientHandler {
    void handle(Command command);
}
