package com.company;

import com.company.controllers.interfaces.*;

import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MyApplication {
    private final Scanner scanner = new Scanner(System.in);

    private final IUserController userController;
    private final IDoctorController doctorController;
    private final IDoctorAvailabilityController availabilityController;
    private final IAppointmentController appointmentController;

    public MyApplication(
            IUserController userController,
            IDoctorController doctorController,
            IDoctorAvailabilityController availabilityController,
            IAppointmentController appointmentController
    ) {
        this.userController = userController;
        this.doctorController = doctorController;
        this.availabilityController = availabilityController;
        this.appointmentController = appointmentController;
    }

    public void start() {
        while (true) {
            System.out.println("\n========== HOSPITAL SYSTEM ==========");
            System.out.println("1) Users");
            System.out.println("2) Doctors");
            System.out.println("3) Doctor Availability");
            System.out.println("4) Appointments");
            System.out.println("0) Exit");
            System.out.print("Choose: ");

            int choice = readInt();
            switch (choice) {
                case 1 -> usersMenu();
                case 2 -> doctorsMenu();
                case 3 -> availabilityMenu();
                case 4 -> appointmentsMenu();
                default -> { return; }
            }
        }
    }

    // -------- USERS --------
    private void usersMenu() {
        while (true) {
            System.out.println("\n--- USERS ---");
            System.out.println("1) Get all users");
            System.out.println("2) Get user by id");
            System.out.println("3) Create user");
            System.out.println("0) Back");
            System.out.print("Choose: ");

            int choice = readInt();
            switch (choice) {
                case 1 -> System.out.println(userController.getAllUsers());
                case 2 -> {
                    System.out.print("User id: ");
                    int id = readInt();
                    System.out.println(userController.getUser(id));
                }
                case 3 -> {
                    System.out.print("Name: ");
                    String name = readLine();
                    System.out.print("Surname: ");
                    String surname = readLine();
                    System.out.print("Gender (male/female): ");
                    String gender = readLine();
                    System.out.println(userController.createUser(name, surname, gender));
                }
                default -> { return; }
            }
        }
    }

    // -------- DOCTORS --------
    private void doctorsMenu() {
        while (true) {
            System.out.println("\n--- DOCTORS ---");
            System.out.println("1) Get all doctors");
            System.out.println("2) Get doctor by id");
            System.out.println("3) Create doctor");
            System.out.println("0) Back");
            System.out.print("Choose: ");

            int choice = readInt();
            switch (choice) {
                case 1 -> System.out.println(doctorController.getAllDoctors());
                case 2 -> {
                    System.out.print("Doctor id: ");
                    int id = readInt();
                    System.out.println(doctorController.getDoctor(id));
                }
                case 3 -> {
                    System.out.print("First name: ");
                    String fn = readLine();
                    System.out.print("Last name: ");
                    String ln = readLine();
                    System.out.print("Specialization: ");
                    String spec = readLine();
                    System.out.print("Email: ");
                    String email = readLine();
                    System.out.print("Phone: ");
                    String phone = readLine();
                    System.out.print("Is active (true/false): ");
                    boolean active = readBoolean();
                    System.out.println(doctorController.createDoctor(fn, ln, spec, email, phone, active));
                }
                default -> { return; }
            }
        }
    }

    // -------- AVAILABILITY --------
    private void availabilityMenu() {
        while (true) {
            System.out.println("\n--- DOCTOR AVAILABILITY ---");
            System.out.println("1) Add availability window");
            System.out.println("2) View doctor availability");
            System.out.println("0) Back");
            System.out.print("Choose: ");

            int choice = readInt();
            switch (choice) {
                case 1 -> {
                    System.out.print("Doctor id: ");
                    int doctorId = readInt();
                    System.out.print("Day (Monday/Tuesday/...): ");
                    String day = readLine();
                    System.out.print("Start time (HH:mm or H:mm): ");
                    String start = readLine();
                    System.out.print("End time (HH:mm or H:mm): ");
                    String end = readLine();
                    System.out.println(availabilityController.addAvailability(doctorId, day, start, end));
                }
                case 2 -> {
                    System.out.print("Doctor id: ");
                    int doctorId = readInt();
                    System.out.println(availabilityController.viewAvailability(doctorId));
                }
                default -> { return; }
            }
        }
    }

    // -------- APPOINTMENTS --------
    private void appointmentsMenu() {
        while (true) {
            System.out.println("\n--- APPOINTMENTS ---");
            System.out.println("1) Create appointment");
            System.out.println("2) View appointments by user");
            System.out.println("3) View appointments by doctor");
            System.out.println("0) Back");
            System.out.print("Choose: ");

            int choice = readInt();
            switch (choice) {
                case 1 -> {
                    System.out.print("User id: ");
                    int userId = readInt();
                    System.out.print("Doctor id: ");
                    int doctorId = readInt();
                    System.out.print("Availability id: ");
                    int availabilityId = readInt();
                    System.out.print("Appointment datetime (yyyy-MM-dd HH:mm): ");
                    String dt = readLine();
                    System.out.print("Duration minutes: ");
                    int dur = readInt();
                    System.out.println(appointmentController.createAppointment(userId, doctorId, availabilityId, dt, dur));
                }
                case 2 -> {
                    System.out.print("User id: ");
                    int userId = readInt();
                    System.out.println(appointmentController.getAppointmentsByUser(userId));
                }
                case 3 -> {
                    System.out.print("Doctor id: ");
                    int doctorId = readInt();
                    System.out.println(appointmentController.getAppointmentsByDoctor(doctorId));
                }
                default -> { return; }
            }
        }
    }

    // -------- helpers --------
    private int readInt() {
        while (true) {
            try {
                int val = Integer.parseInt(readLine().trim());
                return val;
            } catch (Exception e) {
                System.out.print("Enter a number: ");
            }
        }
    }

    private boolean readBoolean() {
        while (true) {
            String s = readLine().trim().toLowerCase();
            if (s.equals("true") || s.equals("t") || s.equals("yes") || s.equals("y")) return true;
            if (s.equals("false") || s.equals("f") || s.equals("no") || s.equals("n")) return false;
            System.out.print("Enter true/false: ");
        }
    }

    private String readLine() {
        return scanner.nextLine();
    }
}