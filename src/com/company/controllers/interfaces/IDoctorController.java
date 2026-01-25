package com.company.controllers.interfaces;

import com.company.models.Doctor;
import java.util.List;

public interface IDoctorController {
    List<Doctor> getDoctorsBySpecialization(String specialization);
    Doctor getDoctorById(int doctorId);
}