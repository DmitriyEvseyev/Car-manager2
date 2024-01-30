package com.dmitriyevseyev.carmanager2.client.network.handlers;

import com.dmitriyevseyev.carmanager2.client.controller.ClientController;
import com.dmitriyevseyev.carmanager2.client.view.utils.RefreshHelper;
import com.dmitriyevseyev.carmanager2.shared.model.Car;
import com.dmitriyevseyev.carmanager2.shared.model.Command;

import java.util.*;


public class GetAllCarsClientHandler implements ClientHandler {

    @Override
    public void handle(Command command) {
        List<Car> carL = (List<Car>) command.getData();

        ClientController.getInstance().setCarList(carL);
        System.out.println("GetAllCarsClientHandler - " + carL);
        RefreshHelper.getInstance().getControllerView().refresh();
    }
}


