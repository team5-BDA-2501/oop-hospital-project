package com.company.controllers.interfaces;

import com.company.models.Appointment;
import java.util.List;

public interface IAppointmentController {
    String createAppointment(int userId, int doctorId, int availabilityId, int timeId, String dateTime, int duration);
    List<Appointment> getAppointmentsByUser(int userId);
    List<Appointment> getAppointmentsByDoctor(int doctorId);
    String cancelAppointment(int appointmentId);
}