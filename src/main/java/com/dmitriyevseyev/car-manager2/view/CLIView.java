package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;


public class CLIView extends javafx.application.Application {

    public static void run(String[] args) {
        System.out.println(1);
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        System.out.println(2);
        Parent root = FXMLLoader.load(getClass().getResource("view.fxml"));
        System.out.println(3);
        stage.setTitle("Car manager");
        stage.setScene(new Scene(root));
        stage.show();
        RefreshHelper.getInstance().getController().refresh();
    }
}

