package com.dmitriyevseyev.carmanager2.server.serverController;

import com.dmitriyevseyev.carmanager2.server.dao.DAOCar;
import com.dmitriyevseyev.carmanager2.server.dao.DAOManager;
import com.dmitriyevseyev.carmanager2.server.exceptions.carExceptions.*;
import com.dmitriyevseyev.carmanager2.shared.Car;

import java.sql.SQLException;
import java.util.*;

public class ServerCarController {
    private static ServerCarController instance;
    private DAOCar daoCar;

    public static ServerCarController getInstance() {
        if (instance == null) {
            instance = new ServerCarController();
        }
        return instance;
    }

    public DAOCar getDaoCar() {
        return daoCar;
    }

    private ServerCarController() {
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
            daoCar.createCar(car);
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
            daoCar.delete(id);
        } catch (SQLException e) {
            throw new DeleteCarExeption(String.format("Error: %s. Code: %s", e.getMessage(), e.getSQLState()));
        }
    }

    public void updateCar(Car car) throws UpdateCarException {
        // dao
        Car updateCar;
        try {
            updateCar = Car.builder()
                    .id(car.getId())
                    .name(car.getName())
                    .date(car.getDate())
                    .color(car.getColor())
                    .isAfterCrash(car.isAfterCrash())
                    .build();
            daoCar.update(updateCar);
        } catch (SQLException e) {
            throw new UpdateCarException(String.format("Error: %s. Code: %s", e.getMessage(), e.getSQLState()));
        }
    }
}
