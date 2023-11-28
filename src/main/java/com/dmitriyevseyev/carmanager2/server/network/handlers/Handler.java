package com.dmitriyevseyev.carmanager2.server.network.handlers;

import com.dmitriyevseyev.carmanager2.shared.Command;

public interface Handler {
    public String handle(Command command);
}
