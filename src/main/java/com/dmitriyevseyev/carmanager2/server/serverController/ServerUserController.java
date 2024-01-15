package com.dmitriyevseyev.carmanager2.server.serverController;

import com.dmitriyevseyev.carmanager2.server.dao.DAOManager;
import com.dmitriyevseyev.carmanager2.server.dao.DAOUser;
import com.dmitriyevseyev.carmanager2.server.exceptions.carExceptions.AddCarExeption;
import com.dmitriyevseyev.carmanager2.server.exceptions.carExceptions.DeleteCarExeption;
import com.dmitriyevseyev.carmanager2.server.exceptions.carExceptions.GetAllCarExeption;
import com.dmitriyevseyev.carmanager2.server.exceptions.carExceptions.NotFoundException;
import com.dmitriyevseyev.carmanager2.shared.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ServerUserController {
    private static ServerUserController instance;
    private DAOUser daoUser;

    public static ServerUserController getInstance() {
        if (instance == null) {
            instance = new ServerUserController();
        }
        return instance;
    }

    public DAOUser getDaoUser() {
        return daoUser;
    }

    private ServerUserController() {
        this.daoUser = DAOManager.getInstance().getDaoUser();
    }

    public boolean isUserExistServer(User user) {
        boolean isCorrect = false;
        try {
            String password = user.getPassword();
            String passwordServer = daoUser.getPassword(user.getUserName());
            if (password.equals(passwordServer)) {
                isCorrect = true;
            }
        } catch (SQLException e) {
            System.out.println("getPasswordError. " + e.getMessage());
        }
        return isCorrect;
    }

    public List<User> getAllUsers() throws GetAllCarExeption {
        // dao
        try {
            return Collections.unmodifiableList(new ArrayList<>(daoUser.getAll()));
        } catch (SQLException e) {
            throw new GetAllCarExeption(String.format("Error: %s. Code: %s", e.getMessage(), e.getSQLState()));
        }
    }

    public void addUser(User user) throws AddCarExeption {
        // dao
        try {
            daoUser.createUser(user);
        } catch (SQLException e) {
            throw new AddCarExeption(String.format("Error: %s. Code: %s", e.getMessage(), e.getSQLState()));
        }
    }

    public void removeCar(String userName) throws NotFoundException, DeleteCarExeption {
        //dao
        try {
            if (!daoUser.isUserExist(userName)) {
                throw new NotFoundException();
            }
            daoUser.delete(userName);
        } catch (SQLException e) {
            throw new DeleteCarExeption(String.format("Error: %s. Code: %s", e.getMessage(), e.getSQLState()));
        }
    }

   /* public void updateCar(Car car) throws UpdateCarException {
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

    */
}

