package com.company.controllers;

import com.company.models.Doctor;
import com.company.repositories.interfaces.IDoctorRepository;

public class AdminController {
    private final IDoctorRepository doctorRepository;

    public AdminController(IDoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    // View all users (implement logic as needed)
    public void viewAllUsers() {
        System.out.println("Displaying all users...");
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