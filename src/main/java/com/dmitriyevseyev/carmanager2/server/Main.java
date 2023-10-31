package com.dmitriyevseyev.carmanager2.server;

import com.dmitriyevseyev.carmanager2.server.DAOManager;
import com.dmitriyevseyev.carmanager2.shared.Car;
import com.dmitriyevseyev.carmanager2.client.CLIView;

public class Main {
    public static void main(String[] args) {
        DAOManager.getInstance();
        CLIView.run(args);
        Car car = Car.builder().id(2).name("k").build();
        System.out.println(car);

    }
}
