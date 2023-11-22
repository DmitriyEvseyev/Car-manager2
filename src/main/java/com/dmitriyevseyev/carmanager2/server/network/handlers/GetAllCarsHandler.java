package com.dmitriyevseyev.carmanager2.server.network.handlers;

import com.dmitriyevseyev.carmanager2.server.controller.Controller;
import com.dmitriyevseyev.carmanager2.server.exceptions.GetAllCarExeption;
import com.dmitriyevseyev.carmanager2.shared.Command;
import com.dmitriyevseyev.carmanager2.shared.CommandId;
import com.dmitriyevseyev.carmanager2.shared.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.ArrayList;


public class GetAllCarsHandler implements Handler {

    @Override
    public String handle() {
       /* ArrayList<Test> testAr = new ArrayList<>();
        testAr.add(new Test(7, "sdds"));
        testAr.add(new Test(8,"ercrc"));
        System.out.println(testAr);


        */
        String answer = null;
        try {
            System.out.println("Controller.getInstance().getAllCars() - " + Controller.getInstance().getAllCars());
            Command command = new Command(CommandId.GET_ALL_CARS, Controller.getInstance().getAllCars());
            ObjectMapper mapper = new ObjectMapper().findAndRegisterModules().disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            answer = mapper.writeValueAsString(command);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (GetAllCarExeption e) {
            e.printStackTrace();
        }
        return answer;
    }
}