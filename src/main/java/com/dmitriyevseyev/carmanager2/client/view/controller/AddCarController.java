package com.dmitriyevseyev.carmanager2.client.view.controller;

import com.dmitriyevseyev.carmanager2.client.controller.ClientController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Locale;

public class AddCarController {

    @FXML
    private TextField nameField;
    @FXML
    private DatePicker dp;
    @FXML
    private TextField colorField;
    @FXML
    private CheckBox isAfterCrashField;

    private Stage dialogStage;
    String date;

    @FXML
    public void ShowDate(javafx.event.ActionEvent actionEvent) {
        LocalDate ld = dp.getValue();
        date = ld.toString();
    }

    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private void handleOk() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        if (isInputValid()) {
            try {
                ClientController.getInstance().addCar(
                        nameField.getText(),
                        formatter.parse(date),
                        colorField.getText(),
                        isAfterCrashField.isSelected());
            } catch (ParseException e) {
                System.out.println(e.getMessage());
            }

            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /* Проверяет пользовательский ввод в текстовых полях. return true, если пользовательский ввод корректен*/
    private boolean isInputValid() {
        String errorMessage = "";

        if (nameField.getText() == null || nameField.getText().length() == 0) {
            errorMessage += "No valid name!\n";
        }
        if (date == null || date.length() == 0) {
            errorMessage += "Invalid date!\n";
        }
        if (colorField.getText() == null || colorField.getText().length() == 0) {
            errorMessage += "Invalid color!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }
}
