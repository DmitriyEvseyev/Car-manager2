
package com.dmitriyevseyev.carmanager2.client.network;

import com.dmitriyevseyev.carmanager2.shared.Command;
import com.dmitriyevseyev.carmanager2.shared.CommandId;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import static com.dmitriyevseyev.carmanager2.shared.Constants.SERVER_PORT;
import static com.dmitriyevseyev.carmanager2.shared.Constants.SERVER_URL;

public class ClientFacade {
    private static ClientFacade instance;
    private Socket socket;

    public static ClientFacade getInstance() {
        if (instance == null) {
            instance = new ClientFacade();
        }

        return instance;
    }

    private ClientFacade() {
    }

    public Socket getSocket() {
        return this.socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void connect() {
        try {
            this.socket = new Socket(SERVER_URL, Integer.parseInt(SERVER_PORT));
            System.out.println("The client is running!");

            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {

                Command command = new Command(CommandId.GET_ALL_CARS, "");
                ObjectMapper mapper = (new ObjectMapper()).findAndRegisterModules().disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
                String request = mapper.writeValueAsString(command);
                System.out.println(request);
                out.write(request + "\n");
                out.flush();
                String responce = in.readLine();
                ObjectMapper mapper2 = (new ObjectMapper()).findAndRegisterModules().disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
                Command commandresponce = (Command) mapper2.readValue(responce, Command.class);
                CommandManagerClient.getInstance(this.socket).processCommand(commandresponce);
                System.out.println("commandresponce - " + String.valueOf(commandresponce));


            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
