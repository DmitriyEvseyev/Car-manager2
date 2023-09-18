package daointerfaces;

import model.Car;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface DAOInterface {
    public void createCar(Integer Id, String Name, Date date, String color, boolean isAfterCrash) throws SQLException;

    public Car read(Integer id) throws SQLException;

    public void update(Car Car) throws SQLException;

    public void delete(Integer id) throws SQLException;

    public List<Car> getAll() throws SQLException;

    public List<Car> getSortedByCriteria(Integer Id, String column, String criteria) throws SQLException;

}
