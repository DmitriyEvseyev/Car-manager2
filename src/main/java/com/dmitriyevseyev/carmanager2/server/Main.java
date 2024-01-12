package com.dmitriyevseyev.carmanager2.server;

import com.dmitriyevseyev.carmanager2.server.dao.DAOManager;
import com.dmitriyevseyev.carmanager2.server.network.SevserFacade;

public class Main {
    public static void main(String[] args) {
        DAOManager.getInstance();
        SevserFacade.getInstance().connect();
    }
}
