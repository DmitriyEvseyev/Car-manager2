package com.dmitriyevseyev.carmanager2.client.view.authorization;

import com.dmitriyevseyev.carmanager2.client.network.ClientSendler;
import com.dmitriyevseyev.carmanager2.shared.Command;
import com.dmitriyevseyev.carmanager2.shared.CommandId;
import com.dmitriyevseyev.carmanager2.shared.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewUserController {

    @FXML
    private TextField password;

    @FXML
    private TextField repeatPassword;

    @FXML
    private TextField userName;

    private Stage dialogStage;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private void initialize() {
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            String name = userName.getText();
            System.out.println(name);
            String pass = password.getText();
            System.out.println(pass);
            User user = User.builder().
                    userName(name).
                    password(pass).
                    build();

            System.out.println(user);
            ClientSendler.getInstance().send(new Command(CommandId.NEW_USER, user));
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
        if (repeatPassword.getText() == null || repeatPassword.getText().length() == 0) {
            errorMessage += "The repeat password has not been entered!\n";
        }
        if (!password.getText().equals(repeatPassword.getText())) {
            errorMessage += "Passwords don't match!";
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