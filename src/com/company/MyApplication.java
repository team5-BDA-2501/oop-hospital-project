package com.company;

import com.company.controllers.*;
import com.company.models.Role;
import com.company.models.User;
import com.company.utils.InputValidator;
import com.company.models.Doctor;

import java.util.List;
import java.util.Scanner;

public class MyApplication {
    private final Scanner scanner = new Scanner(System.in);

    private final UserController userController;
    private final AppointmentController appointmentController;
    private final DoctorController doctorController;
    private final AdminController adminController;

    private User loggedInUser = null;

    private Integer loggedInDoctorTableId = null;

    public MyApplication(UserController userController,
                         AppointmentController appointmentController,
                         DoctorController doctorController,
                         AdminController adminController) {
        this.userController = userController;
        this.appointmentController = appointmentController;
        this.doctorController = doctorController;
        this.adminController = adminController;
    }

    public void start() {
        while (true) {
            System.out.println("========== HOSPITAL SYSTEM ==========");
            System.out.println("1) Login");
            System.out.println("2) Register (USER)");
            System.out.println("0) Exit");
            System.out.print("Choose: ");

            int choice = readInt();
            switch (choice) {
                case 1 -> login();
                case 2 -> registerUser();
                case 0 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void login() {
        System.out.println("----- Login -----");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        loggedInUser = userController.loginUser(username, password);

        if (loggedInUser == null) {
            System.out.println("Invalid username or password.");
            return;
        }

        loggedInDoctorTableId = null;

        System.out.println("Login successful! Welcome " + loggedInUser.getName() + " (" + loggedInUser.getRole() + ")");

        if (loggedInUser.getRole() == Role.ADMIN) {
            adminMenu();
        } else if (loggedInUser.getRole() == Role.DOCTOR) {
            System.out.print("Enter your Doctor ID (from doctors table): ");
            loggedInDoctorTableId = readInt();
            doctorMenu();
        } else {
            userMenu();
        }
    }

    private void registerUser() {
        System.out.println("----- Register USER -----");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Surname: ");
        String surname = scanner.nextLine();

        System.out.print("Username: ");
        String username = scanner.nextLine();
        if (!InputValidator.validUsername(username)) {
            System.out.println("Invalid username (min 3 chars).");
            return;
        }

        System.out.print("Password: ");
        String password = scanner.nextLine();
        if (!InputValidator.validPassword(password)) {
            System.out.println("Invalid password (min 3 chars).");
            return;
        }

        System.out.print("Gender (true for male, false for female): ");
        boolean gender = readBoolean();

        boolean success = userController.registerUser(name, surname, username, password, gender);
        System.out.println(success ? "Registration successful! Please log in." : "Registration failed.");
    }

    private void userMenu() {
        while (true) {
            System.out.println("\n========== USER MENU ==========");
            System.out.println("1) Create Appointment");
            System.out.println("2) Cancel Appointment");
            System.out.println("3) View My Appointments");
            System.out.println("4) Logout");
            System.out.print("Choose: ");

            int c = readInt();
            switch (c) {
                case 1 -> createAppointmentFlow();
                case 2 -> cancelAppointmentFlow();
                case 3 -> appointmentController.viewAppointmentsByUser(loggedInUser.getId());
                case 4 -> {
                    loggedInUser = null;
                    loggedInDoctorTableId = null;
                    System.out.println("Logged out.");
                    return;
                }
                default -> System.out.println("Invalid.");
            }
        }
    }

    private void adminMenu() {
        while (true) {
            System.out.println("\n========== ADMIN MENU ==========");
            System.out.println("1) View all users");
            System.out.println("2) View all appointments (JOIN)");
            System.out.println("3) Add doctor");
            System.out.println("4) Delete doctor");
            System.out.println("5) Logout");
            System.out.print("Choose: ");

            int c = readInt();
            switch (c) {
                case 1 -> adminController.viewAllUsers();
                case 2 -> adminController.viewAllAppointmentsJoined();
                case 3 -> addDoctorFlow();
                case 4 -> deleteDoctorFlow();
                case 5 -> {
                    loggedInUser = null;
                    loggedInDoctorTableId = null;
                    System.out.println("Logged out.");
                    return;
                }
                default -> System.out.println("Invalid.");
            }
        }
    }

    private void doctorMenu() {
        while (true) {
            System.out.println("\n========== DOCTOR MENU ==========");
            System.out.println("1) Add availability");
            System.out.println("2) View my appointments");
            System.out.println("3) Cancel appointment");
            System.out.println("4) Logout");
            System.out.print("Choose: ");

            int c = readInt();
            switch (c) {
                case 1 -> addAvailabilityFlow();
                case 2 -> {
                    if (loggedInDoctorTableId == null) {
                        System.out.println("Doctor ID is not set. Please re-login.");
                    } else {
                        appointmentController.getAppointmentsByDoctor(loggedInDoctorTableId)
                                .forEach(System.out::println);
                    }
                }
                case 3 -> cancelAppointmentFlow();
                case 4 -> {
                    loggedInUser = null;
                    loggedInDoctorTableId = null;
                    System.out.println("Logged out.");
                    return;
                }
                default -> System.out.println("Invalid.");
            }
        }
    }

    private void createAppointmentFlow() {
        System.out.println("----- Create Appointment -----");
        System.out.print("Enter doctor specialization (category): ");
        String specialization = scanner.nextLine();

        List<Doctor> doctors = doctorController.getDoctorsBySpecialization(specialization);
        if (doctors.isEmpty()) {
            System.out.println("No doctors found.");
            return;
        }

        System.out.println("Doctors:");
        doctors.forEach(d -> System.out.println("Doctor ID: " + d.getId() + " | " + d.getFirstName() + " " + d.getLastName()));

        System.out.print("Enter Doctor ID: ");
        int doctorId = readInt();

        System.out.print("Enter appointment datetime (yyyy-MM-dd HH:mm): ");
        String dt = scanner.nextLine();

        int availabilityId = 1;
        int timeId = 1;
        int duration = 30;

        String res = appointmentController.createAppointment(loggedInUser.getId(), doctorId, availabilityId, timeId, dt, duration);
        System.out.println(res);
    }

    private void cancelAppointmentFlow() {
        System.out.println("----- Cancel Appointment -----");
        System.out.print("Appointment ID: ");
        int id = readInt();
        System.out.println(appointmentController.cancelAppointment(id));
    }

    private void addDoctorFlow() {
        System.out.println("----- Add Doctor -----");
        System.out.print("Enter doctor's first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter doctor's last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter doctor's specialization: ");
        String specialization = scanner.nextLine();

        System.out.print("Enter doctor's email: ");
        String email = scanner.nextLine();

        System.out.print("Enter doctor's phone number: ");
        String phone = scanner.nextLine();

        System.out.print("Is the doctor active? (true/false): ");
        boolean isActive = readBoolean();

        String username = firstName.toLowerCase() + lastName.toLowerCase() + "@hospital.com";

        boolean success = doctorController.addDoctor(firstName, lastName, specialization, email, phone, isActive, username);
        System.out.println(success ? "Doctor added successfully." : "Failed to add doctor.");
        System.out.println("Now find the new doctor's ID in DB: SELECT id FROM doctors WHERE email = '" + email + "';");
    }

    private void deleteDoctorFlow() {
        System.out.print("Enter Doctor ID to delete: ");
        int doctorId = readInt();
        boolean success = doctorController.deleteDoctor(doctorId);
        System.out.println(success ? "Doctor deleted successfully." : "Failed to delete doctor.");
    }

    private void addAvailabilityFlow() {
        System.out.println("----- Add Availability -----");

        if (loggedInUser.getRole() != Role.DOCTOR) {
            System.out.println("You are not a doctor and cannot add availability.");
            return;
        }

        if (loggedInDoctorTableId == null) {
            System.out.println("Doctor ID is not set. Please re-login.");
            return;
        }

        System.out.print("Enter the day of the week (e.g., Monday): ");
        String dayOfWeek = scanner.nextLine();

        System.out.print("Enter start time (HH:mm): ");
        String startTime = scanner.nextLine();

        System.out.print("Enter end time (HH:mm): ");
        String endTime = scanner.nextLine();

        boolean success = doctorController.addAvailability(loggedInDoctorTableId, dayOfWeek, startTime, endTime);
        System.out.println(success ? "Availability added successfully." : "Failed to add availability.");
    }

    private int readInt() {
        while (true) {
            try {
                String s = scanner.nextLine();
                return Integer.parseInt(s.trim());
            } catch (Exception e) {
                System.out.print("Enter a number: ");
            }
        }
    }

    private boolean readBoolean() {
        while (true) {
            String s = scanner.nextLine().trim().toLowerCase();
            if (s.equals("true")) return true;
            if (s.equals("false")) return false;
            System.out.print("Type true or false: ");
        }
    }
}