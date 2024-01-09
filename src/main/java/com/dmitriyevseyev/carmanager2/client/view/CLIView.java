package com.dmitriyevseyev.carmanager2.client.view;

import com.dmitriyevseyev.carmanager2.client.network.ClientFasade;
import com.dmitriyevseyev.carmanager2.client.network.ListenerClient;
import com.dmitriyevseyev.carmanager2.client.network.SendlerClient;
import com.dmitriyevseyev.carmanager2.shared.Command;
import com.dmitriyevseyev.carmanager2.shared.CommandId;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;

public class CLIView extends javafx.application.Application {

    public static void run() {
        Application.launch();
    }


    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com.dmitriyevseyev.car-manager2.fxml/view.fxml"));
        stage.setTitle("Car manager");
        stage.setScene(new Scene(root));
        stage.show();
        RefreshHelper.getInstance().getControllerView().refresh();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        Command command = new Command(CommandId.DISCONNECT, "");
        SendlerClient.getInstance().send(command);
        //ListenerClient.getInstance().getObjectInputStream().close();
        //ClientFasade.getInstance().getSocket().close();
    }
}

