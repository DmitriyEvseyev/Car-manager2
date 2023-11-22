package com.dmitriyevseyev.carmanager2.client.network.handlers;

import com.dmitriyevseyev.carmanager2.shared.Car;
import com.dmitriyevseyev.carmanager2.shared.Command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class GetAllCarsHandlerClient implements Handler {


    @Override
    public HashMap<Integer, Car> handle(Command command) {
        Object o = command.getData();
        ArrayList<LinkedHashMap<String, Object>> abc = (ArrayList<LinkedHashMap<String, Object>>) command.getData();
        System.out.println("abc  ===== " + abc);
        Car car = convertObjectToCar(abc.get(0));
        System.out.println("convertObjectToCar ------ " + car);

        /*System.out.println(o.getClass() + " --- " + o);
        ArrayList<Object> list = (ArrayList<Object>) command.getData();
        System.out.println("list  - " + list);

        Object c = list.get(0);
        System.out.println(c.getClass());
        LinkedHashMap<String, Object> l = (LinkedHashMap<String, Object>) c;
        System.out.println("LinkedHashMap<String, Object> -- -" + l);

        Integer f = (Integer) l.get("a");
        String vc = (String) l.get("b");
        System.out.println("test - " + f + "   , " + vc);

         */

        /*List<LinkedHashMap<String, Object>> taskL = (List) command.getAction();
        LinkedHashMap<String, Object> taskList = (LinkedHashMap<String, Object>) o;
        System.out.println("taskList - " + taskList);


         */



        /*System.out.println(car.toString() + "  toString");
        System.out.println("Car + " + car);
        Car s = ((Car) car);
        System.out.println("Car --- " + s);
        if (s instanceof Car) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }

         */

        HashMap<Integer, Car> carMap = new HashMap<>();
        /*for (Car car : cL) {
            carMap.put(car.getId(), car);
        }

         */
        return carMap;
    }

    public Car convertObjectToCar (LinkedHashMap <String, Object> map) {
        System.out.println(map);
        Integer v = Integer.parseInt(map.get("id").toString());
        System.out.println("Integer  " + v);
        System.out.println(map);
        Object rt = map.get("date");
        System.out.println("dffgf"  + rt.toString());
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate plannedDate = LocalDate.parse(map.get("date").toString());
        System.out.println("date" + plannedDate);

        return Car.builder()
                .id(Integer.parseInt(map.get("id").toString()))
                .name((String) map.get("name"))
                .date(LocalDate.parse(map.get("date").toString()))
                .color((String) map.get("color"))
                .isAfterCrash(Boolean.parseBoolean((String) map.get("afterCrash")))
                .build();
    }
}

