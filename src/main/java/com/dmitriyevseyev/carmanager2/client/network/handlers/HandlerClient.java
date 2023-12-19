package com.dmitriyevseyev.carmanager2.client.network.handlers;

import com.dmitriyevseyev.carmanager2.shared.Car;
import com.dmitriyevseyev.carmanager2.shared.Command;

import java.util.HashMap;
import java.util.List;

public interface HandlerClient {
     public  void handle(Command command);
}
