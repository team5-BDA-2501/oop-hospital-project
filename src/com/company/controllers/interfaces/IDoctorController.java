package com.company.controllers.interfaces;

public interface IDoctorController {
    String createDoctor(String name, String surname, String gender, String position);
    String getDoctor(int id);
    String getAllDoctors();
}
