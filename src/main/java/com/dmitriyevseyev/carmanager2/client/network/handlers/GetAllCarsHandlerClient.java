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
        System.out.println("BD - " + carL);
        //CLIView.run();
        /*List<CarFx> carFxList = Converter.getInstance().convertCarListToCarFxList(carL);
        ControllerView controllerView = ControllerView.getInstance();
        controllerView.setRows(carFxList);
        System.out.println("carFxList - " + carFxList);
         */
        HashMap<Integer, Car> map = new HashMap<>();
        for (Car car : carL) {
            map.put(car.getId(), car);
        }

        CarMap.getInstance().setCarMap(map);

        System.out.println("CarMap - " + CarMap.getInstance().getCarMap());

       // CLIView.run();

        RefreshHelper.getInstance().getControllerView().refresh();

    }
}


