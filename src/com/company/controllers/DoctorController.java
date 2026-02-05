package com.company.controllers;

import com.company.models.Doctor;
import com.company.repositories.interfaces.IDoctorRepository;
import java.util.List;  // Make sure you import this

public class DoctorController {
    private final IDoctorRepository doctorRepository;

    public DoctorController(IDoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Doctor getDoctorById(int doctorId) {
        return doctorRepository.getDoctorById(doctorId);
    }

    public List<Doctor> getDoctorsBySpecialization(String specialization) {
        return doctorRepository.getDoctorsBySpecialization(specialization);
    }


    public boolean addDoctor(String firstName, String lastName, String specialization, String email, String phone, boolean isActive, String username) {
        Doctor doctor = new Doctor(firstName, lastName, specialization, email, phone, isActive, username);
        return doctorRepository.addDoctor(doctor);
    }


    public boolean deleteDoctor(int doctorId) {
        return doctorRepository.deleteDoctor(doctorId);  // Ensure this method is correctly implemented in DoctorRepository
    }


    public boolean addAvailability(int doctorId, String dayOfWeek, String startTime, String endTime) {
        return doctorRepository.addAvailability(doctorId, dayOfWeek, startTime, endTime);  // Make sure this is implemented in the repository
    }
}
