package com.company.controllers;

import com.company.controllers.interfaces.IDoctorController;
import com.company.models.Doctor;
import com.company.repositories.interfaces.IDoctorRepository;

import java.util.List;

public class DoctorController implements IDoctorController {
    private final IDoctorRepository repo;

    public DoctorController(IDoctorRepository repo) {
        this.repo = repo;
    }

    @Override
    public String createDoctor(String firstName, String lastName, String specialization, String email, String phone, boolean isActive) {
        Doctor doctor = new Doctor(firstName, lastName, specialization, email, phone, isActive);
        boolean created = repo.createDoctor(doctor);
        return created ? "Doctor was created!" : "Doctor creation failed!";
    }

    @Override
    public String getDoctor(int id) {
        Doctor doctor = repo.getDoctor(id);
        return (doctor == null) ? "Doctor was not found!" : doctor.toString();
    }

    @Override
    public String getAllDoctors() {
        List<Doctor> doctors = repo.getAllDoctors();
        if (doctors == null || doctors.isEmpty()) return "No doctors found!";

        StringBuilder sb = new StringBuilder();
        for (Doctor d : doctors) sb.append(d).append("\n");
        return sb.toString();
    }
}