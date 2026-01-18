package com.company.controllers.interfaces;

import com.company.models.Doctor;

public interface IDoctorController {
    String createDoctor(String name, String surname, String gender, String position);
    String getDoctor(int id);
    String getAllDoctors();

    boolean createDoctor(Doctor doctor);
}
