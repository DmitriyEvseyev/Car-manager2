package com.dmitriyevseyev.carmanager2.client.network.handlers;

import com.dmitriyevseyev.carmanager2.client.MainClient;
import com.dmitriyevseyev.carmanager2.client.view.AddCarController;
import com.dmitriyevseyev.carmanager2.client.view.authorization.NewUserController;
import com.dmitriyevseyev.carmanager2.shared.Command;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterNewUserHandlerClient implements HandlerClient {
    @Override
    public void handle(Command command) {
        try {
            FXMLLoader loader = new FXMLLoader(MainClient.class.getResource("/com.dmitriyevseyev.car-manager2.fxml/newUser.fxml"));
            Scene scene = new Scene(loader.load());

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Registering a new user.");
            dialogStage.setScene(scene);

            NewUserController newUserController = loader.getController();
            newUserController.setDialogStage(dialogStage);

            dialogStage.showAndWait();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

