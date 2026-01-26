package com.company;

import java.util.List;
import java.util.Scanner;
import com.company.controllers.AppointmentController;
import com.company.controllers.UserController;
import com.company.controllers.DoctorController;
import com.company.controllers.DoctorAvailabilityController;
import com.company.models.User;
import com.company.repositories.AppointmentRepository;
import com.company.repositories.UserRepository;
import com.company.repositories.DoctorRepository;
import com.company.repositories.DoctorAvailabilityRepository;
import com.company.data.PostgresDB;
import com.company.models.Doctor;

public class MyApplication {

    private final Scanner scanner = new Scanner(System.in);
    private final UserController userController;
    private final AppointmentController appointmentController;
    private final DoctorController doctorController;
    private final DoctorAvailabilityController doctorAvailabilityController;

    private User loggedInUser = null;


    public MyApplication(UserController userController, AppointmentController appointmentController, DoctorController doctorController, DoctorAvailabilityController doctorAvailabilityController) {
        this.userController = userController;
        this.appointmentController = appointmentController;
        this.doctorController = doctorController;
        this.doctorAvailabilityController = doctorAvailabilityController;
    }

    public void start() {
        while (true) {
            System.out.println("========== HOSPITAL SYSTEM ==========");
            System.out.println("1) Login");
            System.out.println("2) Register");
            System.out.println("0) Exit");
            System.out.print("Choose: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
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

        if (loggedInUser != null) {
            System.out.println("Login successful! Welcome " + loggedInUser.getName());
            showUserMenu();
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private void register() {
        System.out.println("----- Register -----");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Surname: ");
        String surname = scanner.nextLine();
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Gender (true for male, false for female): ");
        boolean gender = scanner.nextBoolean();

        boolean success = userController.registerUser(name, surname, username, password, gender);
        if (success) {
            System.out.println("Registration successful! Please log in.");
        } else {
            System.out.println("Registration failed. Try again.");
        }
    }

    private void showUserMenu() {
        while (true) {
            System.out.println("========== User Menu ==========");
            System.out.println("1) Create Appointment");
            System.out.println("2) Cancel Appointment");
            System.out.println("3) View Appointments");
            System.out.println("4) Log Out");
            System.out.print("Choose: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createAppointment();
                    break;
                case 2:
                    cancelAppointment();
                    break;
                case 3:
                    viewAppointments();
                    break;
                case 4:
                    loggedInUser = null;
                    System.out.println("Logged out successfully.");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void createAppointment() {
        System.out.println("----- Create Appointment -----");
        System.out.print("Enter Doctor Specialization (e.g. Cardiology): ");
        String specialization = scanner.nextLine();


        List<Doctor> doctors = doctorController.getDoctorsBySpecialization(specialization);

        if (doctors.isEmpty()) {
            System.out.println("No doctors found with this specialization.");
        } else {
            System.out.println("Doctors with specialization " + specialization + ":");
            for (Doctor doctor : doctors) {
                System.out.println("Doctor ID: " + doctor.getId() + " | Name: " + doctor.getFirstName() + " " + doctor.getLastName());
            }

            System.out.print("Enter Doctor ID: ");
            int doctorId = scanner.nextInt();
            scanner.nextLine();


            doctorAvailabilityController.viewAvailableSlots(doctorId);

            System.out.print("Enter your preferred slot time: (Ex: 2023-11-05 13:00) ");
            String slotTime = scanner.nextLine();


            String result = appointmentController.createAppointment(loggedInUser.getId(), doctorId, 1, 1, slotTime, 30); // Replace 1 with actual IDs if needed
            System.out.println(result);
        }
    }

    private void cancelAppointment() {
        System.out.println("----- Cancel Appointment -----");

        appointmentController.viewAppointmentsByUser(loggedInUser.getId());

        System.out.print("Enter the ID of the appointment to cancel: ");
        int appointmentId = scanner.nextInt();
        String result = appointmentController.cancelAppointment(appointmentId);
        System.out.println(result);
    }

    private void viewAppointments() {
        System.out.println("----- View Appointments -----");
        appointmentController.viewAppointmentsByUser(loggedInUser.getId());
    }
}