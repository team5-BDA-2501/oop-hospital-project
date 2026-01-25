package com.company.controllers;

import com.company.controllers.interfaces.IAppointmentController;
import com.company.models.Appointment;
import com.company.repositories.interfaces.IAppointmentRepository;
import com.company.repositories.interfaces.IDoctorAvailabilityRepository;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.List;

public class AppointmentController implements IAppointmentController {
    private final IAppointmentRepository appointmentRepo;

    public AppointmentController(IAppointmentRepository appointmentRepo) {
        this.appointmentRepo = appointmentRepo;
    }

    @Override
    public String createAppointment(int userId, int doctorId, int availabilityId, int timeId, String dateTime, int duration) {
        LocalDateTime appointmentDatetime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        Appointment appointment = new Appointment(userId, doctorId, availabilityId, timeId, appointmentDatetime, duration, "Scheduled");

        boolean result = appointmentRepo.createAppointment(appointment);

        if (result) {
            return "Appointment created successfully!";
        } else {
            return "Failed to create appointment.";
        }
    }

    @Override
    public List<Appointment> getAppointmentsByUser(int userId) {
        return appointmentRepo.getAppointmentsByUser(userId);
    }

    @Override
    public List<Appointment> getAppointmentsByDoctor(int doctorId) {
        return appointmentRepo.getAppointmentsByDoctor(doctorId);
    }

    @Override
    public String cancelAppointment(int appointmentId) {
        boolean result = appointmentRepo.cancelAppointment(appointmentId);
        return result ? "Appointment cancelled successfully." : "Failed to cancel appointment.";
    }

    public void viewAppointmentsByUser(int userId) {
        List<Appointment> appointments = appointmentRepo.getAppointmentsByUser(userId);
        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
        } else {
            for (Appointment appointment : appointments) {
                System.out.println(appointment);
            }
        }
    }
}