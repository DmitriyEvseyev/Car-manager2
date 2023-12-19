package com.dmitriyevseyev.carmanager2.client;

import com.dmitriyevseyev.carmanager2.client.network.ClientFasade;
import com.dmitriyevseyev.carmanager2.client.view.CLIView;
import com.dmitriyevseyev.carmanager2.client.view.CarFx;
import com.dmitriyevseyev.carmanager2.client.view.RefreshHelper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainClient  {
    public static void main(String[] args) {
        //CLIView.run();
        ClientFasade.getInstance().connect();

       /* Application.launch(args);

    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com.dmitriyevseyev.car-manager2.fxml/view.fxml"));
        stage.setTitle("Car manager");
        stage.setScene(new Scene(root));
        stage.show();
        RefreshHelper.getInstance().getControllerView().refresh();
    }


        */

    }
}


