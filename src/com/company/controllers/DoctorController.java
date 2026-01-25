package com.company.controllers;

import com.company.controllers.interfaces.IDoctorController;
import com.company.models.Doctor;
import com.company.repositories.interfaces.IDoctorRepository;

import java.util.List;

public class DoctorController implements IDoctorController {

    private final IDoctorRepository doctorRepository;

    public DoctorController(IDoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public List<Doctor> getDoctorsBySpecialization(String specialization) {
        return doctorRepository.getDoctorsBySpecialization(specialization);
    }

    @Override
    public Doctor getDoctorById(int doctorId) {
        return doctorRepository.getDoctorById(doctorId);
    }
}