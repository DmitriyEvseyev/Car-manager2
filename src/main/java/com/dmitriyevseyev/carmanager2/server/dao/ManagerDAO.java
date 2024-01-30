package com.dmitriyevseyev.carmanager2.server.dao;

import com.dmitriyevseyev.carmanager2.shared.utils.Constants;

import java.sql.*;

public class ManagerDAO {
    public static java.sql.Connection connection;
    public static ManagerDAO instance;
    private CarDAO carDAO;
    private UserDAO userDAO;

    public static ManagerDAO getInstance() {
        if (instance == null) {
            instance = new ManagerDAO();
        }
        return instance;
    }

    public CarDAO getDaoCar() {
        return carDAO;
    }

    public UserDAO getDaoUser() {
        return userDAO;
    }

    public ManagerDAO() {

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    Constants.JDBC,
                    Constants.USER,
                    Constants.PASSWORD);
            this.carDAO = CarDAO.getInstance(connection);
            this.userDAO = UserDAO.getInstance(connection);

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");

        try (Statement stmt = connection.createStatement()) {
            String sqlCar = "CREATE TABLE IF NOT EXISTS CAR" +
                    "(ID INT PRIMARY KEY NOT NULL GENERATED BY DEFAULT AS IDENTITY, " +
                    " NAME           VARCHAR     NOT NULL, " +
                    " DATE           DATE        NOT NULL, " +
                    " COLOR          VARCHAR     NOT NULL, " +
                    " ISAFTERCRASH   BOOLEAN)";
            stmt.executeUpdate(sqlCar);

            String sqlUser = "CREATE TABLE IF NOT EXISTS USERS" +
                    "(ID INT PRIMARY KEY NOT NULL GENERATED BY DEFAULT AS IDENTITY, " +
                    " USERNAME       VARCHAR     NOT NULL, " +
                    " PASSWORD       VARCHAR     NOT NULL)";
            stmt.executeUpdate(sqlUser);

        } catch (SQLException e) {
            System.err.println("createTableCarError. " + e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}
