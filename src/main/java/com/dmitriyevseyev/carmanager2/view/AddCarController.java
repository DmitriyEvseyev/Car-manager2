package com.dmitriyevseyev.carmanager2.view;

import com.dmitriyevseyev.carmanager2.daomanager.DAOManager;
import com.dmitriyevseyev.carmanager2.model.Car;
import com.dmitriyevseyev.carmanager2.exceptions.AddCarExeption;
import com.dmitriyevseyev.carmanager2.controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalDate;

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
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    public void ShowDate(javafx.event.ActionEvent actionEvent) {
        LocalDate ld = dp.getValue();
        date = ld.toString();
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            Car car;
            CarFx carFx = null;
            try {
                carFx = CarFx.builder()
                        .id(DAOManager.getInstance().maxIdCar())
                        .name(nameField.getText())
                        .date(date)
                        .color(colorField.getText())
                        .isAfterCrash(isAfterCrashField.isSelected())
                        .build();

            } catch (SQLException e) {
                System.out.println("Id not found. " + e.getMessage());
            }

            ListFx.getInstance().getCarFxList().add(carFx);

            car = Converter.getInstance().convertCarFxToCar(carFx);
            try {
                Controller.getInstance().addCar(car);
            } catch (AddCarExeption e) {
                e.getMessage();
            }

            dialogStage.close();
        }
    }


    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /* Проверяет пользовательский ввод в текстовых полях. * @return true, если пользовательский ввод корректен*/
    private boolean isInputValid() {
        String errorMessage = "";

        if (nameField.getText() == null || nameField.getText().length() == 0) {
            errorMessage += "No valid name!\n";
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
