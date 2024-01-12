package com.dmitriyevseyev.carmanager2.client.network.handlers;

import com.dmitriyevseyev.carmanager2.client.clientController.ClientController;
import com.dmitriyevseyev.carmanager2.client.view.*;
import com.dmitriyevseyev.carmanager2.shared.Car;
import com.dmitriyevseyev.carmanager2.shared.Command;

import java.util.*;


public class GetAllCarsHandlerClient implements HandlerClient {

    @Override
    public void handle(Command command) {
        List<Car> carL = (List<Car>) command.getData();

        ClientController.getInstance().setCarList(carL);
        System.out.println("GetAllCarsHandlerClient - " + carL);
        RefreshHelper.getInstance().getControllerView().refresh();
    }
}


