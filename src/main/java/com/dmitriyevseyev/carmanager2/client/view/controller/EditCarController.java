package com.dmitriyevseyev.carmanager2.client.view.controller;

import com.dmitriyevseyev.carmanager2.client.controller.ClientController;
import com.dmitriyevseyev.carmanager2.client.view.utils.Converter;
import com.dmitriyevseyev.carmanager2.client.view.model.CarFx;
import com.dmitriyevseyev.carmanager2.shared.model.Car;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

public class EditCarController {

    public EditCarController() {
    }

    @FXML
    private TextField nameField;
    @FXML
    private DatePicker dp;
    @FXML
    private TextField colorField;
    @FXML
    private CheckBox isAfterCrashField;

    private Stage dialogStage;
    private CarFx carFx;

    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    // заполняет поля окна редактирования
    public void setCarFx(CarFx carFx) {
        this.carFx = carFx;

        nameField.setText(carFx.getName());
        dp.setValue(LocalDate.parse(carFx.getDate()));
        colorField.setText(carFx.getColor());
        isAfterCrashField.setSelected(carFx.isIsAfterCrash());
    }

    //уставливает новые значения после редкатирования
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            carFx.setName(nameField.getText());
            carFx.setDate(dp.getValue().toString());
            carFx.setColor(colorField.getText());
            carFx.setIsAfterCrash(isAfterCrashField.isSelected());

            Car car = Converter.getInstance().convertCarFxToCar(carFx);
            ClientController.getInstance().updateCar(car);

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
        if (String.valueOf(dp.getValue()) == null || String.valueOf(dp.getValue()).length() == 0) {
            errorMessage += "Invalid date!\n";
        }
        if (colorField.getText() == null || colorField.getText().length() == 0) {
            errorMessage += "Invalid color!\n";
        }

        if (errorMessage.length() == 0) {
            return true;

        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }
}





