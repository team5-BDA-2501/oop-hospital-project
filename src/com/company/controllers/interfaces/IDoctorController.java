package com.company.controllers.interfaces;

public interface IDoctorController {
    String createDoctor(String firstName, String lastName, String specialization, String email, String phone, boolean isActive);
    String getDoctor(int id);
    String getAllDoctors();
}
