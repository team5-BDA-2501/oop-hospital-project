package com.company.repositories.interfaces;

import com.company.models.Doctor;

import java.util.List;

public interface IDoctorRepository {
    boolean createDoctor(Doctor doctor);
    Doctor getDoctor(int id);
    List<Doctor> getAllDoctors();
}

