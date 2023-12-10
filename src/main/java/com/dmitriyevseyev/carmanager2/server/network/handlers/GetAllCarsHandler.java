package com.dmitriyevseyev.carmanager2.server.network.handlers;

import com.dmitriyevseyev.carmanager2.server.controller.Controller;
import com.dmitriyevseyev.carmanager2.server.exceptions.GetAllCarExeption;
import com.dmitriyevseyev.carmanager2.server.network.SevserFasade;
import com.dmitriyevseyev.carmanager2.shared.Command;


public class GetAllCarsHandler implements HandlerServer {

    @Override
    public void handle(Command command) {

        try {
            Command com = new Command(command.getAction(), Controller.getInstance().getAllCars());
            System.out.println("Com responce - " + com);
            SevserFasade.getInstance().sendler(com);

        } catch (GetAllCarExeption e) {
            System.out.println(e.getMessage());
        }
    }
}


