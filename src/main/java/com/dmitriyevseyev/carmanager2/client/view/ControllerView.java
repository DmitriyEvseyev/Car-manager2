package com.dmitriyevseyev.carmanager2.client.view;

import com.dmitriyevseyev.carmanager2.client.controller.ControllerClient;
import com.dmitriyevseyev.carmanager2.client.network.CarMap;
import com.dmitriyevseyev.carmanager2.client.network.ClientFasade;
import com.dmitriyevseyev.carmanager2.shared.Car;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerView implements Initializable {
    private static ControllerView instance;
    private ControllerClient controllerClient;
    private List<CarFx> rows;

    public static ControllerView getInstance() {
        if (instance == null) {
            instance = new ControllerView();
        }

        return instance;
    }

    public ControllerView() {
        this.controllerClient = ControllerClient.getInstance();
        this.rows = new ArrayList<>();
    }

    public List<CarFx> getRows() {
        return rows;
    }

    public void setRows(List<CarFx> rows) {
        this.rows = rows;
    }

    @FXML
    private Button modifyCar;
    @FXML
    private Button deleteCar;
    @FXML
    TableView<CarFx> tableview;

    @FXML
    private TableColumn<CarFx, Integer> idColumn;
    @FXML
    private TableColumn<CarFx, String> nameColumn;
    @FXML
    private TableColumn<CarFx, String> dateColumn;
    @FXML
    private TableColumn<CarFx, String> colorColumn;
    @FXML
    private TableColumn<CarFx, Boolean> isAfterCrashColumn;
    @FXML
    private TableColumn<CarFx, String> actionColumn;

    public void refresh() {
        deleteCar.setDisable(true);
        modifyCar.setDisable(true);

        List<Car> carList = new ArrayList<>(CarMap.getInstance().getCarMap().values());
        rows = Converter.getInstance().convertCarListToCarFxList(carList);
        System.out.println("rows - " + rows);
        for (CarFx carFx : rows) {
            carFx.getCheckBox().setOnAction(actionEvent -> {
                selectedCheckBox();
            });
        }
        tableview.setItems(FXCollections.observableArrayList(rows));
        RefreshHelper.getInstance().setControllerView(this);
    }

    private void selectedCheckBox() {
        int count = 0;

        for (CarFx row : rows) {
            if (row.getCheckBox().isSelected()) {
                ++count;
            }
        }
        if (count == 0) {
            deleteCar.setDisable(true);
            modifyCar.setDisable(true);
        }
        if (count == 1) {
            deleteCar.setDisable(false);
            modifyCar.setDisable(false);
        }
        if (count > 1) {
            deleteCar.setDisable(false);
            modifyCar.setDisable(false);
        }
    }

    @FXML
    public void initialize(URL url, ResourceBundle rb) {

        idColumn.setCellValueFactory(new PropertyValueFactory("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        dateColumn.setCellValueFactory(new PropertyValueFactory("date"));
        colorColumn.setCellValueFactory(new PropertyValueFactory("color"));
        isAfterCrashColumn.setCellValueFactory(new PropertyValueFactory("isAfterCrash"));
        actionColumn.setCellValueFactory(new PropertyValueFactory("checkBox"));

        RefreshHelper.getInstance().setControllerView(this);
    }

    @FXML
    private void deleteSelectedRows(ActionEvent actionEvent) {
        for (CarFx carFx : tableview.getItems()) {
            if (carFx.getCheckBox().isSelected()) {
                controllerClient.removeCar(carFx.getId());
            }
        }
        refresh();
    }

    @FXML
    private void editSelectedRow() {
        for (CarFx carFx : tableview.getItems()) {
            if (carFx.getCheckBox().isSelected()) {
                try {
                    FXMLLoader loader = new FXMLLoader(CLIView.class.getResource("/com.dmitriyevseyev.car-manager2.fxml/carEdit.fxml"));
                    Scene scene = new Scene(loader.load());
                    Stage dialogStage = new Stage();
                    dialogStage.setTitle("Edit Car");
                    dialogStage.setScene(scene);

                    EditCarController editCarcontroller = loader.getController();
                    editCarcontroller.setDialogStage(dialogStage);
                    editCarcontroller.setCarFx(carFx);

                    dialogStage.showAndWait();

                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        refresh();
    }

    @FXML
    private void addRow() {
        try {
            FXMLLoader loader = new FXMLLoader(CLIView.class.getResource("/com.dmitriyevseyev.car-manager2.fxml/carAdd.fxml"));
            Scene scene = new Scene(loader.load());

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add Car");
            dialogStage.setScene(scene);

            AddCarController addCarController = loader.getController();
            addCarController.setDialogStage(dialogStage);

            dialogStage.showAndWait();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        refresh();
    }
}


