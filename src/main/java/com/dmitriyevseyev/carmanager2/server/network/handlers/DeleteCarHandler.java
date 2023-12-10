package com.dmitriyevseyev.carmanager2.server.network.handlers;

import com.dmitriyevseyev.carmanager2.server.controller.Controller;
import com.dmitriyevseyev.carmanager2.server.exceptions.DeleteCarExeption;
import com.dmitriyevseyev.carmanager2.server.exceptions.GetAllCarExeption;
import com.dmitriyevseyev.carmanager2.server.exceptions.NotFoundException;
import com.dmitriyevseyev.carmanager2.server.network.SevserFasade;
import com.dmitriyevseyev.carmanager2.shared.Command;

public class DeleteCarHandler implements HandlerServer {
    @Override
    public void handle(Command command) {

        Integer id = Integer.parseInt((String) command.getData());
        System.out.println("del ID - " + id);
        try {
            Controller.getInstance().removeCar(id);
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (DeleteCarExeption e) {
            System.out.println(e.getMessage());
        }
    }
}
