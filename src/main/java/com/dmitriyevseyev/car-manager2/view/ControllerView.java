package view;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerView implements Initializable {
    public ControllerView() {
    }

    @FXML
    private Label carManager;
    @FXML
    private Button modifyCar;
    @FXML
    private Button add;
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


    private ArrayList<CarFx> rows = new ArrayList<>();

    public void refresh() {
        deleteCar.setDisable(true);
        modifyCar.setDisable(true);
        rows.clear();

        int length = Converter.getInstance().convertCartoCarFx().size();

        for (int i = 0; i < length; i++) {
            CarFx carFx = (CarFx) Converter.getInstance().convertCartoCarFx().get(i);
            carFx.getCheckBox().setSelected(false);
            rows.add(carFx);
            rows.get(i).getCheckBox().setOnAction(actionEvent -> {
                selectedCheckBox();
            });
        }
        tableview.setItems(FXCollections.observableArrayList(rows));
        view.RefreshHelper.getInstance().setController(this);
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

        RefreshHelper.getInstance().setController(this);
    }

    @FXML
    private void deleteSelectedRows(ActionEvent actionEvent) {
        /*int length = tableview.getItems().size();
        for (int i = 0; i < length && length > 0; i++) {
            if (tableview.getItems().get(i).getCheckBox().isSelected()) {
                tableview.getItems().remove(i);
                ListCar.getInstance().getCarList().remove(i);
                --i;
                --length;
            }
            //selectedCheckBox();
        }
        refresh(); */
    }

    @FXML
    private void editSelectedRow() {
        /*
        int length = tableview.getItems().size();
        for (int i = 0; i < length; i++) {
            if (tableview.getItems().get(i).getCheckBox().isSelected()) {
                try {
                    // Загружаем fxml-файл и создаём новую сцену для всплывающего диалогового окна.
                    FXMLLoader loader = new FXMLLoader(Application.class.getResource("carEdit.fxml"));
                    Scene scene = new Scene(loader.load());

                    Stage dialogStage = new Stage();
                    dialogStage.setTitle("Edit Car");
                    dialogStage.setScene(scene);

                    // Передаём адресата в контроллер.
                    EditCarController editCarcontroller = loader.getController();
                    editCarcontroller.setDialogStage(dialogStage);
                    editCarcontroller.setCar(tableview.getItems().get(i));

                    dialogStage.showAndWait();

                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        refresh(); */
    }

    @FXML
    private void addRow() {
        /*
        try {
            // Загружаем fxml-файл и создаём новую сцену для всплывающего диалогового окна.
            FXMLLoader loader = new FXMLLoader(Application.class.getResource("addCar.fxml"));
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
        refresh(); */
    }
}


