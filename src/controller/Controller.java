package controller;

import daomanager.DAOManager;
import exceptions.*;
import model.Car;
import dao.DAOCar;

import java.sql.SQLException;
import java.util.*;

public class Controller {
    // part of singleton pattern. read more: https://habr.com/ru/post/129494/
    private static Controller instance;
    private DAOCar daoCar;

    // singleton pattern
    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public DAOCar getDaoCar() {
        return daoCar;
    }

    // constructor is private because singleton pattern
    private Controller() {
        this.daoCar = DAOManager.getInstance().getDaoCar();
    }

    public List<Car> getAllCars() throws GetAllCarExeption {
        // dao
        // return unmodifiable list of cars because we can change some car only via addCar method or getCar and set required fields
        try {
            return Collections.unmodifiableList(new ArrayList<>(daoCar.getAll()));
        } catch (SQLException e) {
            throw new GetAllCarExeption(e.getMessage());
        }
    }

    public void addCar(Car car) throws AddCarExeption {
        // dao
        try {
            daoCar.createCar(
                    car.getId(),
                    car.getName(),
                    car.getDate(),
                    car.getColor(),
                    car.isAfterCrash());
        } catch (SQLException e) {
            throw new AddCarExeption(e.getMessage());
        }
    }

    public void removeCar(Integer id) throws NotFoundException, DeleteCarExeption {
        //dao
        try {
            if (!daoCar.isCarExist(id)) {
                throw new NotFoundException();
            }
            daoCar.delete(daoCar.read(id));
        } catch (SQLException e) {
            throw new DeleteCarExeption(e.getMessage());
        }
    }

    public void updateCar(Integer id, String Name, Date date, String color, boolean isAfterCrash) throws NotFoundException, UpdateCarException {
        // dao
        Car updateCar;
        try {
            updateCar = Car.builder()
                    .id(id)
                    .name(Name)
                    .date(date)
                    .color(color)
                    .isAfterCrash(isAfterCrash)
                    .build();
            daoCar.update(updateCar);
        } catch (SQLException e) {
            throw new UpdateCarException(e.getMessage());
        }
    }
}

