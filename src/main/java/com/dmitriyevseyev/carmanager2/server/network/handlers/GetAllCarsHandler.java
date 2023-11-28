package com.dmitriyevseyev.carmanager2.server.network.handlers;

import com.dmitriyevseyev.carmanager2.server.controller.Controller;
import com.dmitriyevseyev.carmanager2.server.exceptions.GetAllCarExeption;
import com.dmitriyevseyev.carmanager2.server.network.SevserFasade;
import com.dmitriyevseyev.carmanager2.shared.Command;


public class GetAllCarsHandler implements Handler {

    @Override
    public String handle(Command comamd) {
        String answer = null;
        try {
            System.out.println("Controller - " + Controller.getInstance().getAllCars());
            Command com = new Command(comamd.getAction(), Controller.getInstance().getAllCars());
            System.out.println("Com responce - " + com);
            SevserFasade.getInstance().sendler(com);

        } catch (GetAllCarExeption e) {
            e.printStackTrace();
        }
        return answer;
    }
}


