package com.dmitriyevseyev.carmanager2.server;

import com.dmitriyevseyev.carmanager2.server.dao.ManagerDAO;
import com.dmitriyevseyev.carmanager2.server.network.ServerFacade;

public class Server {
    public static void main(String[] args) {
        ManagerDAO.getInstance();
        ServerFacade.getInstance().connect();
    }
}
