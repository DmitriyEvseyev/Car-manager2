package com.dmitriyevseyev.carmanager2.client.network.handlers;

import com.dmitriyevseyev.carmanager2.shared.model.Command;
import javafx.application.Platform;

import javafx.scene.control.Alert;

public class RegisterNewUserClientHandler implements ClientHandler {
    @Override
    public void handle(Command command) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("authorization");
            alert.setContentText("The data is entered incorrectly!");
            alert.showAndWait();
        });
    }
}


