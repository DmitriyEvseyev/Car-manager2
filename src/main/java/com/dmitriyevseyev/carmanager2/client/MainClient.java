package com.dmitriyevseyev.carmanager2.client;

import com.dmitriyevseyev.carmanager2.client.network.ClientFasade;
import com.dmitriyevseyev.carmanager2.client.network.ClientSendler;
import com.dmitriyevseyev.carmanager2.shared.Command;
import com.dmitriyevseyev.carmanager2.shared.CommandId;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainClient extends javafx.application.Application {
    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/com.dmitriyevseyev.car-manager2.fxml/view.fxml"));
        stage.setTitle("Car manager");
        stage.setScene(new Scene(root));
        stage.show();
        ClientFasade.getInstance().connect();
       }

    @Override
    public void stop() throws Exception {
        super.stop();
        Command command = new Command(CommandId.DISCONNECT, "");
        ClientSendler.getInstance().send(command);
       }
}