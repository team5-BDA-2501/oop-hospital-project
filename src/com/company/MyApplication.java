package com.company;

import com.company.controllers.interfaces.IDoctorController;
import com.company.controllers.interfaces.IUserController;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MyApplication {
    private final Scanner scanner = new Scanner(System.in);

    private final IUserController userController;
    private final IDoctorController doctorController;

    public MyApplication(IUserController userController, IDoctorController doctorController) {
        this.userController = userController;
        this.doctorController = doctorController;
    }

    private void mainMenu() {
        System.out.println();
        System.out.println("Welcome to My Application");
        System.out.println("Select option:");
        System.out.println("1. Get all users");
        System.out.println("2. Get user by id");
        System.out.println("3. Create user");
        System.out.println("4. Get all doctors");
        System.out.println("5. Get doctor by id");
        System.out.println("6. Create doctor");
        System.out.println("0. Exit");
        System.out.println();
        System.out.print("Enter option (1-6): ");
    }

    public void start() {
        while (true) {
            mainMenu();
            try {
                int option = scanner.nextInt();

                switch (option) {
                    case 1: getAllUsersMenu(); break;
                    case 2: getUserByIdMenu(); break;
                    case 3: createUserMenu(); break;
                    case 4: getAllDoctorsMenu(); break;
                    case 5: getDoctorByIdMenu(); break;
                    case 6: createDoctorMenu(); break;
                    default: return;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input must be integer: " + e);
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            System.out.println("*************************");
        }
    }

    private void getAllUsersMenu() {
        System.out.println(userController.getAllUsers());
    }

    private void getUserByIdMenu() {
        System.out.println("Please enter user id:");
        int id = scanner.nextInt();
        System.out.println(userController.getUser(id));
    }

    private void createUserMenu() {
        System.out.println("Please enter name:");
        String name = scanner.next();
        System.out.println("Please enter surname:");
        String surname = scanner.next();
        System.out.println("Please enter gender (male/female):");
        String gender = scanner.next();

        System.out.println(userController.createUser(name, surname, gender));
    }

    private void getAllDoctorsMenu() {
        System.out.println(doctorController.getAllDoctors());
    }

    private void getDoctorByIdMenu() {
        System.out.println("Please enter doctor id:");
        int id = scanner.nextInt();
        System.out.println(doctorController.getDoctor(id));
    }

    private void createDoctorMenu() {
        System.out.println("Please enter name:");
        String name = scanner.next();
        System.out.println("Please enter surname:");
        String surname = scanner.next();
        System.out.println("Please enter gender (male/female):");
        String gender = scanner.next();
        System.out.println("Please enter position:");
        String position = scanner.next();

        System.out.println(doctorController.createDoctor(name, surname, gender, position));
    }
}
