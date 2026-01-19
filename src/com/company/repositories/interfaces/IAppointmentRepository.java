package com.company.repositories.interfaces;

import com.company.models.Appointment;

import java.util.List;

public interface IAppointmentRepository {
    boolean createAppointment(Appointment appointment);
    List<Appointment> getAppointmentsByUser(int userId);
    List<Appointment> getAppointmentsByDoctor(int doctorId);
}