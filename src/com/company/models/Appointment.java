package com.company.models;

import java.time.LocalDateTime;

public class Appointment {
    private int id;
    private int userId;
    private int doctorId;
    private int availabilityId;
    private int timeId;
    private LocalDateTime appointmentDatetime;
    private int durationMinutes;
    private String status;

    // Constructor for creating appointments
    public Appointment(int id, int userId, int doctorId, int availabilityId, int timeId,
                       LocalDateTime appointmentDatetime, int durationMinutes, String status) {
        this.id = id;
        this.userId = userId;
        this.doctorId = doctorId;
        this.availabilityId = availabilityId;
        this.timeId = timeId;
        this.appointmentDatetime = appointmentDatetime;
        this.durationMinutes = durationMinutes;
        this.status = status;
    }

    // Constructor for creating a new Appointment without the ID (for new appointments)
    public Appointment(int userId, int doctorId, int availabilityId, int timeId,
                       LocalDateTime appointmentDatetime, int durationMinutes, String status) {
        this(0, userId, doctorId, availabilityId, timeId, appointmentDatetime, durationMinutes, status);
    }

    // Getters for the fields
    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public int getAvailabilityId() {
        return availabilityId;
    }

    public int getTimeId() {
        return timeId;
    }

    public LocalDateTime getAppointmentDatetime() {
        return appointmentDatetime;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public String getStatus() {
        return status;
    }

    // Optional: Override toString() for easy printing
    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", userId=" + userId +
                ", doctorId=" + doctorId +
                ", availabilityId=" + availabilityId +
                ", timeId=" + timeId +
                ", appointmentDatetime=" + appointmentDatetime +
                ", durationMinutes=" + durationMinutes +
                ", status='" + status + '\'' +
                '}';
    }
}