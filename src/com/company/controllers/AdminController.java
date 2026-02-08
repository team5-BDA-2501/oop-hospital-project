package com.company.controllers;
import com.company.models.User;
import com.company.repositories.interfaces.IUserRepository;
import com.company.repositories.interfaces.IDoctorRepository;
import com.company.models.Doctor;
import com.company.models.User;
import com.company.repositories.*;
import com.company.repositories.interfaces.IDoctorRepository;

import java.util.List;

public class AdminController {
    private final IDoctorRepository doctorRepository;
    private final IUserRepository userRepository;

    public AdminController(IDoctorRepository doctorRepository, IUserRepository userReporsitory) {
        this.doctorRepository = doctorRepository;
        this.userRepository = userReporsitory;
    }


    public void viewAllUsers() {
        System.out.println("Displaying all users...");


        List<User> users = userRepository.getAllUsers();


        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            System.out.println("List of Users:");
            for (User user : users) {
                System.out.println("User ID: " + user.getId() + " | Username: " + user.getUsername() + " | Role: " + user.getRole());
            }
        }
    }

    public void viewAllAppointmentsJoined() {
        System.out.println("Displaying all appointments with user and doctor details...");
    }


    public boolean addDoctor(Doctor doctor) {
        return doctorRepository.addDoctor(doctor);
    }


    public boolean deleteDoctor(int doctorId) {
        return doctorRepository.deleteDoctor(doctorId);
    }
}