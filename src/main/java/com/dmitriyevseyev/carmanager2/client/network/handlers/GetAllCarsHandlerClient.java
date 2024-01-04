package com.dmitriyevseyev.carmanager2.client.network.handlers;

import com.dmitriyevseyev.carmanager2.client.network.CarMap;
import com.dmitriyevseyev.carmanager2.client.view.*;
import com.dmitriyevseyev.carmanager2.shared.Car;
import com.dmitriyevseyev.carmanager2.shared.Command;

import java.util.*;


public class GetAllCarsHandlerClient implements HandlerClient {

    @Override
    public void handle(Command command) {
        List<Car> carL = (List<Car>) command.getData();

        HashMap<Integer, Car> map = new HashMap<>();
        for (Car car : carL) {
            map.put(car.getId(), car);
        }
        CarMap.getInstance().setCarMap(map);
        System.out.println("CarMap (GetAllCarsHandlerClient) - " + CarMap.getInstance().getCarMap());

        RefreshHelper.getInstance().getControllerView().setRows(Converter.getInstance().convertCarListToCarFxList(carL));
        RefreshHelper.getInstance().getControllerView().refresh();
    }
}


