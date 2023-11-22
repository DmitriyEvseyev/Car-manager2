package com.dmitriyevseyev.carmanager2.server.network;

import com.dmitriyevseyev.carmanager2.shared.Command;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static com.dmitriyevseyev.carmanager2.shared.Constants.SERVER_PORT;

public class SevserFasade {
    private static SevserFasade instance;
    private static Socket clientSocket;
    private static ServerSocket server;

    public static SevserFasade getInstance() {
        if (instance == null) {
            instance = new SevserFasade();
        }
        return instance;
    }

    private SevserFasade() {
    }

    public void connect() {
        try {
            server = new ServerSocket(Integer.parseInt(SERVER_PORT));
            System.out.println("The server is running!");
            clientSocket = server.accept();

            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {

                String request = in.readLine();
                System.out.println(request);
                ObjectMapper mapper = new ObjectMapper().findAndRegisterModules().disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
                Command command = mapper.readValue(request, Command.class);

                System.out.println(command);

                CommandManagerServer.getInstance(clientSocket).processCommand(command);




                /* System.out.println("The server received command: " + command);
                if (command == CommandId.GET_ALL_CARS) {

                    List<Car> carList = Controller.getInstance().getAllCars();

                    System.out.println("server : ");
                    for (Car car : carList) {
                        System.out.println(car);
                    }
                    out.write(carList + "\n");
                    out.flush();
                }
                 */
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void sendler () {

    }
}