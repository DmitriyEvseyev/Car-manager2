package view;

import controller.Controller;
import controller.IDGenerator;
import model.Car;
import exceptions.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class CLIView {
    private static CLIView instance;

    private Scanner scanner;
    private Controller controller;
    private IDGenerator idGenerator;

    public static CLIView getInstance(IDGenerator idG) {
        if (instance == null) {
            instance = new CLIView(idG);
        }
        return instance;
    }

    private CLIView(IDGenerator Idgen) {
        this.scanner = new Scanner(System.in);
        this.controller = Controller.getInstance();
        this.idGenerator = Idgen;
    }

    public void run() {
        int menuAction = -1;

        System.out.println("***** Car daomanager.Manager *****");
        while (menuAction != 5) {
            menuAction = runMenu();

            switch (menuAction) {
                case 1:
                    showCars(); // dao
                    break;
                case 2:
                    createCar(); // dao
                    break;
                case 3:
                    deleteCar(); // dao
                    break;
                case 4:
                    modifyCar();  // dao
                    break;
                case 5:
                    System.out.println("\n Good bye! See you soon :)");
                    break;
                default:
                    System.out.println("Error: Incorrect value, please choose correct");
                    break;
            }
        }
    }

    private int runMenu() {
        System.out.println();
        System.out.println("Choose action:");
        System.out.println("1. Display all cars");
        System.out.println("2. Create car");
        System.out.println("3. Delete car");
        System.out.println("4. Modify car");
        System.out.println("5. Exit");

        System.out.print("\nAction: ");
        String input = scanner.nextLine();
        return Integer.valueOf(input);
    }

    private void showCars() {
        System.out.println("Cars:\n");
        try {
            for (Car i : controller.getAllCars()) {
                System.out.println(i);
            }
            controller.getAllCars();
            System.out.print("Press \"Enter\" for return to the main menu...\n");
            scanner.nextLine();
        } catch (GetAllCarExeption e) {
            System.out.println(e.getMessage());
        }
    }

    private void createCar() {
        System.out.println("\nNew car:");
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        Date date;
        while (true) {
            System.out.print("Enter date (yyyy-mm-dd): ");
            String productionDate = scanner.nextLine();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
            try {
                date = formatter.parse(productionDate);
                System.out.println("Correct date");
                break;
            } catch (ParseException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.print("Enter color: ");
        String color = scanner.nextLine();
        System.out.print("Does car have crashes (yes/no): ");
        String crashes = scanner.nextLine();
        boolean isAfterCrash = false;
        if ("yes".equalsIgnoreCase(crashes)) {
            isAfterCrash = true;
        }
        try {
            controller.addCar(
                    Car.builder()
                            .id(idGenerator.getId())
                            .name(name)
                            .date(date)
                            .color(color)
                            .isAfterCrash(isAfterCrash)
                            .build()
            );
            System.out.println("\nInfo: Car has been added!");
        } catch (AddCarExeption e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteCar() {
        System.out.println("Which car to sell?");
        System.out.print("Enter id: ");
        Integer id = Integer.valueOf(scanner.nextLine());

        try {
            controller.removeCar(id);
            System.out.println("the car has been deleted:  " + id);
        } catch (NotFoundException e) {
            System.out.println("there is no such car in the database, try again");
        } catch (DeleteCarExeption e) {
            System.out.println(e.getMessage());
        }
    }

    public void modifyCar() {
        System.out.print("Choose a car. Enter id: ");
        String ID = scanner.nextLine();
        Integer id = Integer.valueOf(ID);

        try {
            if (!controller.getDaoCar().isCarExist(id)) {
                throw new NotFoundException();
            }
            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            Date date;
            while (true) {
                System.out.print("Enter date (yyyy-mm-dd): ");
                String productionDate = scanner.nextLine();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
                try {
                    date = formatter.parse(productionDate);
                    System.out.println("Correct date");
                    break;
                } catch (ParseException e) {
                    System.out.println(e.getMessage());
                }
            }
            System.out.print("Change the color:");
            String color = scanner.nextLine();
            System.out.println("Does car have crashes (yes/no)?");
            String crashes = scanner.nextLine();
            boolean isAfterCrash = false;
            if ("yes".equalsIgnoreCase(crashes)) {
                isAfterCrash = true;
            }
            try {
                controller.updateCar(id, name, date, color, isAfterCrash);
                System.out.println("Changes have been made");
            } catch (UpdateCarException e) {
                System.out.println("UpdateCarException. " + e.getMessage());
            }
        } catch (NotFoundException e) {
            System.out.println("there is no such car in the database, try again");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
