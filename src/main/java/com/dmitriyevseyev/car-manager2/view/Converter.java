package view;

import java.util.ArrayList;
import java.util.List;

import controller.Controller;
import exceptions.GetAllCarExeption;


public class Converter {
    private static Converter instance;
    private Controller controller;

    public static Converter getInstance() {
        if (instance == null) {
            instance = new Converter();
        }
        return instance;
    }

    private Converter() {
        this.controller = Controller.getInstance();
    }

    public List convertCartoCarFx() {
        List listCar = new ArrayList<>();
        try {
            for (int i = 0; i < controller.getAllCars().size(); i++) {

                listCar.add(new CarFx(controller.getAllCars().get(i).getId(),
                        controller.getAllCars().get(i).getName(),
                        controller.getAllCars().get(i).getColor(),
                        controller.getAllCars().get(i).getDate().toString(),
                        controller.getAllCars().get(i).isAfterCrash()));
            }
        } catch (GetAllCarExeption e) {
            System.out.println(e.getMessage());
        }
        return listCar;
    }
}
