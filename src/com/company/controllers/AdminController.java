package com.company.controllers;

import com.company.models.AppointmentDetails;
import com.company.models.Doctor;
import com.company.models.User;
import com.company.repositories.interfaces.IAppointmentRepository;
import com.company.repositories.interfaces.IDoctorRepository;
import com.company.repositories.interfaces.IUserRepository;

import java.util.List;

public class AdminController {

    private final IDoctorRepository doctorRepository;
    private final IUserRepository userRepository;
    private final IAppointmentRepository appointmentRepository;

    public AdminController(IDoctorRepository doctorRepository,
                           IUserRepository userRepository,
                           IAppointmentRepository appointmentRepository) {
        this.doctorRepository = doctorRepository;
        this.userRepository = userRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public void viewAllUsers() {
        System.out.println("Displaying all users...");

        List<User> users = userRepository.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("No users found.");
            return;
        }

        System.out.println("List of Users:");
        for (User user : users) {
            System.out.println("User ID: " + user.getId()
                    + " | Username: " + user.getUsername()
                    + " | Role: " + user.getRole());
        }
    }

    public void viewAllAppointmentsJoined() {
        System.out.println("Displaying all appointments with user and doctor details...");

        List<AppointmentDetails> list = appointmentRepository.getAllAppointmentsDetails();
        if (list.isEmpty()) {
            System.out.println("No appointments found.");
            return;
        }


        list.forEach(System.out::println);
    }

    public boolean addDoctor(Doctor doctor) {
        return doctorRepository.addDoctor(doctor);
    }

    public boolean deleteDoctor(int doctorId) {
        return doctorRepository.deleteDoctor(doctorId);
    }
}