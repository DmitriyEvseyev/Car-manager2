package com.dmitriyevseyev.carmanager2.client.network;

import com.dmitriyevseyev.carmanager2.shared.Car;

import java.util.HashMap;

public class CarMap {
    private static CarMap instance;
    private HashMap<Integer, Car> carMap;

    private CarMap() {
    }

    public static CarMap getInstance() {
        if (instance == null) {
            instance = new CarMap();
        }
        return instance;
    }

    public HashMap<Integer, Car> getCarMap() {
        return carMap;
    }

    public void setCarMap(HashMap<Integer, Car> carMap) {
        this.carMap = carMap;
    }
}
