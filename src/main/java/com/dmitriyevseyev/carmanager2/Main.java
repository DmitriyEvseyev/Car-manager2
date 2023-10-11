package com.dmitriyevseyev.carmanager2;

import com.dmitriyevseyev.carmanager2.controller.IDGenerator;
import com.dmitriyevseyev.carmanager2.daomanager.DAOManager;
import com.dmitriyevseyev.carmanager2.view.CLIView;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        DAOManager.getInstance();
        Integer StartId = 0;

        try {
            StartId = DAOManager.getInstance().maxIdCar();
        } catch (SQLException e) {
            System.out.println("Id not found. " + e.getMessage());
        }

        IDGenerator idGeneratorXXX = IDGenerator.getInstance(StartId);

        CLIView.run(args);
    }
}
