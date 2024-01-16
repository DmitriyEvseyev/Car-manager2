package com.dmitriyevseyev.carmanager2.client.network.handlers;

import com.dmitriyevseyev.carmanager2.client.MainClient;
import com.dmitriyevseyev.carmanager2.client.view.AddCarController;
import com.dmitriyevseyev.carmanager2.client.view.ControllerView;
import com.dmitriyevseyev.carmanager2.client.view.authorization.NewUserController;
import com.dmitriyevseyev.carmanager2.shared.Command;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterNewUserHandlerClient implements HandlerClient {
    @Override
    public void handle(Command command) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("authorization");
            alert.setContentText("A user with this name is not registered!");
            alert.showAndWait();
        });
    }
}


