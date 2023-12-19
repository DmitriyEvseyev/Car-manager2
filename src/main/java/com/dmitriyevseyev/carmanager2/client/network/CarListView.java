package com.dmitriyevseyev.carmanager2.client.network;

import com.dmitriyevseyev.carmanager2.shared.Car;

import java.util.List;

public class CarListView {
    private static CarListView instance;
    private List<Car> carList;

    private CarListView() {
    }

    public static CarListView getInstance() {
        if (instance == null) {
            instance = new CarListView();
        }
        return instance;
    }

    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }
}
