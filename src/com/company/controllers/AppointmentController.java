package com.company.controllers;

import com.company.models.AppointmentDetails;
import com.company.repositories.interfaces.IAppointmentRepository;
import com.company.models.Appointment;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AppointmentController {
    private final IAppointmentRepository appointmentRepo;

    public AppointmentController(IAppointmentRepository appointmentRepo) {
        this.appointmentRepo = appointmentRepo;
    }


    public String createAppointment(int userId, int doctorId, int availabilityId, int timeId, String dateTime, int duration) {
        LocalDateTime appointmentDatetime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Appointment appointment = new Appointment(userId, doctorId, availabilityId, timeId, appointmentDatetime, duration, "Scheduled");

        boolean result = appointmentRepo.createAppointment(appointment);
        return result ? "Appointment created successfully!" : "Failed to create appointment.";
    }


    public List<AppointmentDetails> getAppointmentsByUser(int userId) {
        return appointmentRepo.getAppointmentsByUser(userId);
    }

    public List<AppointmentDetails> getAppointmentsByDoctor(int doctorId) {
        return appointmentRepo.getAppointmentsByDoctor(doctorId);
    }


    public String cancelAppointment(int appointmentId) {
        boolean result = appointmentRepo.cancelAppointment(appointmentId);
        return result ? "Appointment cancelled successfully." : "Failed to cancel appointment.";
    }


    public void viewAppointmentsByUser(int userId) {
        List<AppointmentDetails> appointments = getAppointmentsByUser(userId);
        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
        } else {
            appointments.forEach(System.out::println);  // Printing all appointments for user
        }
    }
}