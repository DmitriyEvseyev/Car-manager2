package com.dmitriyevseyev.carmanager2.client.network.handlers;

import com.dmitriyevseyev.carmanager2.shared.Car;
import com.dmitriyevseyev.carmanager2.shared.Command;
import javafx.scene.control.Alert;

import java.util.List;

public class ErrorHandlerClient implements HandlerClient{
    @Override
    public void handle(Command command) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ErrorHandlerClient");
        alert.setHeaderText(String.valueOf(command.getData()));
        alert.showAndWait();
    }
}
