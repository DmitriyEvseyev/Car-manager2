package com.dmitriyevseyev.carmanager2.server.network.handlers;

import com.dmitriyevseyev.carmanager2.server.controller.Controller;
import com.dmitriyevseyev.carmanager2.server.exceptions.DeleteCarExeption;
import com.dmitriyevseyev.carmanager2.server.exceptions.GetAllCarExeption;
import com.dmitriyevseyev.carmanager2.server.exceptions.NotFoundException;
import com.dmitriyevseyev.carmanager2.server.network.SevserFasade;
import com.dmitriyevseyev.carmanager2.shared.Command;
import com.dmitriyevseyev.carmanager2.shared.CommandId;

public class DeleteCarHandler implements HandlerServer {
    @Override
    public void handle(Command command) {

        Integer id = (Integer) command.getData();
        System.out.println("del ID - " + id);
        try {
            Controller.getInstance().removeCar(id);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        } catch (DeleteCarExeption e) {
            Command error = new Command(CommandId.ERROR,"error DeleteCar,  " + e.getMessage());
            SevserFasade.getInstance().sendler(error);
            System.out.println(e.getMessage());
        }
    }
}
