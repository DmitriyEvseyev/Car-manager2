package view;

import java.util.ArrayList;
import java.util.List;

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
}
