package com.dmitriyevseyev.carmanager2.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;


public class CLIView extends javafx.application.Application {

    public static void run(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com.dmitriyevseyev.car-manager2.fxml/view.fxml"));
        stage.setTitle("Car manager");
        stage.setScene(new Scene(root));
        stage.show();
        RefreshHelper.getInstance().getControllerView().refresh();
    }
}

