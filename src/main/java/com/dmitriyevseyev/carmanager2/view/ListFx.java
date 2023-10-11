package com.dmitriyevseyev.carmanager2.view;

import java.util.ArrayList;

public class ListFx {
    private static ListFx instance;
    private ArrayList<CarFx> carFxList;

    public static ListFx getInstance() {
        if (instance == null) {
            instance = new ListFx();
        }
        return instance;
    }

    private ListFx() {
        this.carFxList = new ArrayList<>();
    }

    public ArrayList<CarFx> getCarFxList() {
        return carFxList;
    }

    public void setCarFxList(ArrayList<CarFx> carFxList) {
        this.carFxList = carFxList;
    }

    public  ArrayList addListFx () {
        return Converter.getInstance().convertCarToCarFx(carFxList);
    }
}
