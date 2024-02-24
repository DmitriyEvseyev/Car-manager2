package com.dmitriyevseyev.carmanager2.client;

import com.dmitriyevseyev.carmanager2.client.controller.ClientController;
import com.dmitriyevseyev.carmanager2.client.network.ClientFacade;
import com.dmitriyevseyev.carmanager2.client.network.ClientSendler;
import com.dmitriyevseyev.carmanager2.shared.model.Command;
import com.dmitriyevseyev.carmanager2.shared.model.CommandId;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Client extends javafx.application.Application {
    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage stage)  {

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/com.dmitriyevseyev.car-manager2.fxml/view.fxml"));
        } catch (IOException e) {
            System.out.println("IOException, Client. " + e.getMessage());
        }
        stage.setTitle("Car manager");
        stage.setScene(new Scene(root));
        stage.show();
        ClientFacade.getInstance().connect();
    }

    @Override
    public void stop()  {
        try {
            super.stop();
        } catch (Exception e) {
            System.out.println("Exception, Client, stop. " + e.getMessage());
        }
        Integer idUser = ClientController.getInstance().getIdUser();
        Command command = new Command(CommandId.DISCONNECT, idUser);
        ClientSendler.getInstance().send(command);
    }
}