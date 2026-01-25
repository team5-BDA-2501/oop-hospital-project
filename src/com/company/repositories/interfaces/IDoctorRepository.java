package com.company.repositories.interfaces;

import com.company.models.Doctor;

import java.util.List;

public interface IDoctorRepository {
    Doctor getDoctorById(int doctorId);
    List<Doctor> getDoctorsBySpecialization(String specialization);  // Add this method signature
}