package com.dmitriyevseyev.carmanager2.client.network.handlers;

import com.dmitriyevseyev.carmanager2.shared.Car;
import com.dmitriyevseyev.carmanager2.shared.Command;

import java.util.HashMap;

public interface Handler {
     HashMap<Integer, Car> handle(Command command);
}
