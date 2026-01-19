package com.company.controllers;

import com.company.controllers.interfaces.IAppointmentController;
import com.company.models.Appointment;
import com.company.repositories.interfaces.IAppointmentRepository;

import java.time.LocalDateTime;
import java.util.List;

public class AppointmentController implements IAppointmentController {

    private final IAppointmentRepository repo;

    public AppointmentController(IAppointmentRepository repo) {
        this.repo = repo;
    }

    @Override
    public String createAppointment(int userId, int doctorId,
                                    int availabilityId,
                                    String dateTime,
                                    int duration) {
        Appointment a = new Appointment(
                0,
                userId,
                doctorId,
                availabilityId,
                LocalDateTime.parse(dateTime.replace(" ", "T")),
                duration,
                "Scheduled"
        );
        return repo.createAppointment(a)
                ? "Appointment created"
                : "Failed to create appointment";
    }

    @Override
    public String getAppointmentsByUser(int userId) {
        return format(repo.getAppointmentsByUser(userId));
    }

    @Override
    public String getAppointmentsByDoctor(int doctorId) {
        return format(repo.getAppointmentsByDoctor(doctorId));
    }

    private String format(List<Appointment> list) {
        if (list.isEmpty()) return "No appointments found.";
        StringBuilder sb = new StringBuilder();
        list.forEach(a -> sb.append(a).append("\n"));
        return sb.toString();
    }
}