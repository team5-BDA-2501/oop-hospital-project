package com.company.repositories.interfaces;

import com.company.models.Doctor;
import java.util.List;

public interface IDoctorRepository {
    Doctor getDoctorById(int doctorId);

    List<Doctor> getDoctorsBySpecialization(String specialization);

    // Add a doctor
    boolean addDoctor(Doctor doctor);

    // Delete a doctor
    boolean deleteDoctor(int doctorId);

    // Add availability for a doctor
    boolean addAvailability(int doctorId, String dayOfWeek, String startTime, String endTime);
}