package com.company.repositories.interfaces;

import com.company.models.Appointment;
import com.company.models.AppointmentDetails;
import java.util.List;
public interface IAppointmentRepository {
    boolean createAppointment(Appointment appointment);
    boolean cancelAppointment(int appointmentId);
    List<AppointmentDetails> getAppointmentsByUser(int userId);
    List<Appointment> getAppointmentsByDoctor(int doctorId);
    List<AppointmentDetails> getAllAppointmentsDetails();
}