package controller;

import daomanager.DAOManager;
import exceptions.DeleteCarExeption;
import exceptions.NotFoundException;
import exceptions.UpdateCarException;
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

    // todo read comments for updateCar method about exception
    public List<Car> getAllCars() throws SQLException {
        // dao
        // return unmodifiable list of cars because we can change some car only via addCar method or getCar and set required fields
        return Collections.unmodifiableList(new ArrayList<>(daoCar.getAll()));
    }

    // todo read comments for updateCar method about exception
    public void addCar(Car car) throws SQLException {
        // dao
        daoCar.createCar(
                car.getId(),
                car.getName(),
                car.getDate(),
                car.getColor(),
                car.isAfterCrash());
    }

    // todo SQLException should be catched and message should go to the NotFoundException
    public void removeCar(Integer id) throws NotFoundException,  DeleteCarExeption {
        //dao
        try {
            if (!daoCar.isCarExist(id)) {
                throw new NotFoundException();
            }
            daoCar.delete(daoCar.read(id));
        } catch (SQLException e) {
            throw new DeleteCarExeption();
        }
    }

    // todo need to throw custom exception - UpdateCarException
    public void updateCar(Integer id, String Name, Date date, String color, boolean isAfterCrash) throws NotFoundException, UpdateCarException {
        // dao
        Car updateCar;
        try {
            if (!daoCar.isCarExist(id)) {
                throw new NotFoundException();
            }
            updateCar = Car.builder()
                    .id(id)
                    .name(Name)
                    .date(date)
                    .color(color)
                    .isAfterCrash(isAfterCrash)
                    .build();
            daoCar.update(updateCar);
        } catch (SQLException e) {
            throw new UpdateCarException();
        }
    }
}

