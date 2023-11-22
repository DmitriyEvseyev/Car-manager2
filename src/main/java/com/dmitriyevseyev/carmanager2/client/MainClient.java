package com.dmitriyevseyev.carmanager2.client;

import com.dmitriyevseyev.carmanager2.client.network.ClientFacade;
import com.dmitriyevseyev.carmanager2.client.view.CLIView;

public class MainClient {
    public static void main(String[] args) {
        //CLIView.run(args);
        ClientFacade.getInstance().connect();
    }
}
