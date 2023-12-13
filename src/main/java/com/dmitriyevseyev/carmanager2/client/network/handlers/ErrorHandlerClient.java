package com.dmitriyevseyev.carmanager2.client.network.handlers;

import com.dmitriyevseyev.carmanager2.shared.Car;
import com.dmitriyevseyev.carmanager2.shared.Command;

import java.util.List;

public class ErrorHandlerClient implements HandlerClient{
    @Override
    public List<Car> handle(Command command) {
        return null;
    }
}
