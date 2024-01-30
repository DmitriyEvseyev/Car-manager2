package com.dmitriyevseyev.carmanager2.server.dao;

import com.dmitriyevseyev.carmanager2.shared.model.Car;

import java.sql.SQLException;
import java.util.List;

public interface DAO {
    void createCar(Car car) throws SQLException;

    Car read(Integer id) throws SQLException;

    void update(Car Car) throws SQLException;

    void delete(Integer id) throws SQLException;

    List<Car> getAll() throws SQLException;

    List<Car> getSortedByCriteria(Integer Id, String column, String criteria) throws SQLException;

}
