package com.dmitriyevseyev.carmanager2.client.network.handlers;

import com.dmitriyevseyev.carmanager2.shared.Car;
import com.dmitriyevseyev.carmanager2.shared.Command;

import java.util.*;


public class GetAllCarsHandlerClient implements HandlerClient {

    @Override
    public List<Car> handle(Command command) {
        List<Car> carL = (List<Car>) command.getData();
        return carL;
    }
}


