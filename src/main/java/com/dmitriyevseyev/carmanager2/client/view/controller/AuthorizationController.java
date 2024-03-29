package com.dmitriyevseyev.carmanager2.client.view.controller;

import com.dmitriyevseyev.carmanager2.client.network.ClientSendler;
import com.dmitriyevseyev.carmanager2.shared.model.Command;
import com.dmitriyevseyev.carmanager2.shared.model.CommandId;
import com.dmitriyevseyev.carmanager2.shared.model.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AuthorizationController implements Initializable {
    private Stage dialogStage;

    @FXML
    private TextField userName;

    @FXML
    private TextField password;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            String name = userName.getText();
            String pass = password.getText();
            User user = User.builder().
                    userName(name).
                    password(pass).
                    build();
            System.out.println(user);

            ClientSendler.getInstance().send(new Command(CommandId.AUTHORIZATION, user));
            dialogStage.close();
        }
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (userName.getText() == null || userName.getText().length() == 0) {
            errorMessage += "User name not entered!\n";
        }
        if (password.getText() == null || password.getText().length() == 0) {
            errorMessage += "The password has not been entered!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }

}
