package view;

import controller.Controller;
import exceptions.AddCarExeption;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Car;
import view.CarFx;

import java.time.LocalDate;

public class AddCarController {
    @FXML
    private TextField idField;
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

    public void ShowDate(javafx.event.ActionEvent actionEvent) {
        LocalDate ld = dp.getValue();
        date = ld.toString();
    }


    @FXML
    private void handleOk() {
        if (isInputValid()) {
            Car car;
            CarFx carFx = new CarFx(
                    Integer.parseInt((idField.getText())),
                    nameField.getText(),
                    date,
                    colorField.getText(),
                    isAfterCrashField.isSelected());

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

        if (idField.getText() == null || idField.getText().length() == 0) {
            errorMessage += "Invalid Id!\n";
        } else {
            try {
                Integer.parseInt((idField.getText()));
            } catch (NumberFormatException e) {
                errorMessage += "No valid (id must be an integer)!\n";
            }
        }
        if (nameField.getText() == null || nameField.getText().length() == 0) {
            errorMessage += "No valid name!\n";
        }
        if (colorField.getText() == null || colorField.getText().length() == 0) {
            errorMessage += "Invalid color!\n";
        }


        /* if (isAfterCrashField.getText() == null || isAfterCrashField.getText().length() == 0) {
            errorMessage += "Invalid isAfterCrashField!\n";
        } else {
            try {
                Boolean.parseBoolean((isAfterCrashField.getText()));
            } catch (NumberFormatException e) {
                errorMessage += "No valid postal code (must be an integer)!\n";
            }
        } */

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
