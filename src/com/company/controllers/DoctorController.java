package com.company.controllers;

import com.company.models.Doctor;
import com.company.controllers.interfaces.IDoctorController;
import com.company.repositories.interfaces.IDoctorRepository;

import java.util.List;

public class DoctorController implements IDoctorController {
    private final IDoctorRepository repo;

    public DoctorController(IDoctorRepository repo) {
        this.repo = repo;
    }

    public String createDoctor(String name, String surname, String gender, String position) {
        boolean male = gender.equalsIgnoreCase("male");
        Doctor doctor = new Doctor(name, surname, male, position);

        boolean created = repo.createDoctor(doctor);

        return (created ? "User was created!" : "User creation was failed!");
    }

    public String getDoctor(int id) {
        Doctor doctor = repo.getDoctor(id);

        return (doctor == null ? "User was not found!" : doctor.toString());
    }

    public String getAllDoctors() {
        List<Doctor> doctors = repo.getAllDoctors();
        if (doctors == null || doctors.isEmpty()) return "No doctors found!";

        StringBuilder response = new StringBuilder();
        for (Doctor doctor : doctors) {
            response.append(doctor).append("\n");
        }
        return response.toString();
    }
}
