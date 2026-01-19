package com.company.controllers.interfaces;

public interface IDoctorAvailabilityController {
    String addAvailability(int doctorId, String day, String start, String end);
    String viewAvailability(int doctorId);
}