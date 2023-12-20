package com.dmitriyevseyev.carmanager2.client;

import com.dmitriyevseyev.carmanager2.client.network.ClientFasade;


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


