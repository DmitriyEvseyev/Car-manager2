package com.dmitriyevseyev.carmanager2.client;

import com.dmitriyevseyev.carmanager2.client.network.ClientFasade;
import com.dmitriyevseyev.carmanager2.client.view.CLIView;


public class MainClient {
    public static void main(String[] args) {
        ClientFasade.getInstance().connect();
        CLIView.run();
    }
}


