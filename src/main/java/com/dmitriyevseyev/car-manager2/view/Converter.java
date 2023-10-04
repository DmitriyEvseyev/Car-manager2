package view;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import controller.Controller;
import exceptions.GetAllCarExeption;
import model.Car;


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
        ArrayList<CarFx> carFxConvertn = ListFx.getInstance().getCarFxList();

        try {
            for (int i = 0; i < controller.getAllCars().size(); i++) {

                carFxConvertn.add(new CarFx(controller.getAllCars().get(i).getId(),
                        controller.getAllCars().get(i).getName(),
                        controller.getAllCars().get(i).getColor(),
                        controller.getAllCars().get(i).getDate().toString(),
                        controller.getAllCars().get(i).isAfterCrash()));
            }
        } catch (GetAllCarExeption e) {
            System.out.println(e.getMessage());
        }
        return carFxConvertn;
    }

    public Car convertCarFxToCar (CarFx carFx) {
        Car car = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
        try {
            car = Car.builder()
                    .id(carFx.getId())
                    .name(carFx.getName())
                    .date(formatter.parse(carFx.getDate()))
                    .color(carFx.getColor())
                    .isAfterCrash(carFx.isIsAfterCrash())
                    .build();
        } catch (ParseException e) {
            e.getMessage();
        }
        return car;
    }
}
