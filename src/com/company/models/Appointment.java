package com.company.models;

import java.time.LocalDateTime;

public class Appointment {
    private int userId;
    private int doctorId;
    private int availabilityId;
    private int timeId;
    private LocalDateTime appointmentDatetime;
    private int durationMinutes;
    private String status;

    // Constructor
    public Appointment(int userId, int doctorId, int availabilityId, int timeId, LocalDateTime appointmentDatetime, int durationMinutes, String status) {
        this.userId = userId;
        this.doctorId = doctorId;
        this.availabilityId = availabilityId;
        this.timeId = timeId;
        this.appointmentDatetime = appointmentDatetime;
        this.durationMinutes = durationMinutes;
        this.status = status;
    }

    // Getters (add methods like these)
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
    @Override
    public String toString() {
        return "Appointment{" +
                "userId=" + userId +
                ", doctorId=" + doctorId +
                ", availabilityId=" + availabilityId +
                ", timeId=" + timeId +
                ", appointmentDatetime=" + appointmentDatetime +
                ", durationMinutes=" + durationMinutes +
                ", status='" + status + '\'' +
                '}';
    }
}