package com.company.controllers.interfaces;

public interface IAppointmentController {

    String createAppointment(
            int userId,
            int doctorId,
            int availabilityId,
            String dateTime,
            int duration
    );

    String getAppointmentsByUser(int userId);
    String getAppointmentsByDoctor(int doctorId);
}
