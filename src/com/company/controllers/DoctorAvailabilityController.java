package com.company.controllers;

import com.company.controllers.interfaces.IDoctorAvailabilityController;
import com.company.models.DoctorAvailability;
import com.company.repositories.interfaces.IDoctorAvailabilityRepository;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DoctorAvailabilityController implements IDoctorAvailabilityController {

    private final IDoctorAvailabilityRepository repo;
    private static final DateTimeFormatter HH_MM = DateTimeFormatter.ofPattern("H:mm"); // accepts 1:10 and 01:10

    public DoctorAvailabilityController(IDoctorAvailabilityRepository repo) {
        this.repo = repo;
    }

    @Override
    public String addAvailability(int doctorId, String day, String start, String end) {
        try {
            LocalTime st = LocalTime.parse(start.trim(), HH_MM);
            LocalTime en = LocalTime.parse(end.trim(), HH_MM);

            if (!st.isBefore(en)) return "Start time must be before end time.";

            DoctorAvailability a = new DoctorAvailability(doctorId, day, st, en);
            return repo.add(a) ? "Availability added." : "Failed to add availability.";
        } catch (Exception e) {
            return "Invalid time format. Use HH:mm (example 09:30) or H:mm (example 9:30).";
        }
    }

    @Override
    public String viewAvailability(int doctorId) {
        List<DoctorAvailability> list = repo.getByDoctor(doctorId);
        if (list == null || list.isEmpty()) return "No availability found.";

        StringBuilder sb = new StringBuilder();
        for (DoctorAvailability a : list) sb.append(a).append("\n");
        return sb.toString();
    }
}