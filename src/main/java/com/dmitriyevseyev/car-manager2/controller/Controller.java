package controller;

import daomanager.DAOManager;
import model.Car;
import dao.DAOCar;
import exceptions.*;
import java.sql.SQLException;
import java.util.*;

public class Controller {
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

    private Controller() {
        this.daoCar = DAOManager.getInstance().getDaoCar();
    }

    public List<Car> getAllCars() throws GetAllCarExeption {
        // dao
        try {
            return Collections.unmodifiableList(new ArrayList<>(daoCar.getAll()));
        } catch (SQLException e) {
            throw new GetAllCarExeption(String.format("Error: %s. Code: %s", e.getMessage(), e.getSQLState()));
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
            throw new AddCarExeption(String.format("Error: %s. Code: %s", e.getMessage(), e.getSQLState()));
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
            throw new DeleteCarExeption(String.format("Error: %s. Code: %s", e.getMessage(), e.getSQLState()));
        }
    }

    public void updateCar(Integer id, String Name, Date date, String color, boolean isAfterCrash) throws  UpdateCarException {
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
            throw new UpdateCarException(String.format("Error: %s. Code: %s", e.getMessage(), e.getSQLState()));
        }
    }
}

