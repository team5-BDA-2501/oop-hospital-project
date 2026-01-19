package com.company.models;

import java.time.LocalDateTime;

public class Appointment {
    private int id;
    private int userId;
    private int doctorId;
    private int availabilityId;
    private LocalDateTime dateTime;
    private int durationMinutes;
    private String status;

    public Appointment(int id, int userId, int doctorId,
                       int availabilityId,
                       LocalDateTime dateTime,
                       int durationMinutes,
                       String status) {
        this.id = id;
        this.userId = userId;
        this.doctorId = doctorId;
        this.availabilityId = availabilityId;
        this.dateTime = dateTime;
        this.durationMinutes = durationMinutes;
        this.status = status;
    }

    public int getUserId() { return userId; }
    public int getDoctorId() { return doctorId; }
    public int getAvailabilityId() { return availabilityId; }
    public LocalDateTime getDateTime() { return dateTime; }
    public int getDurationMinutes() { return durationMinutes; }
    public String getStatus() { return status; }

    @Override
    public String toString() {
        return "Appointment{id=" + id +
                ", userId=" + userId +
                ", doctorId=" + doctorId +
                ", dateTime=" + dateTime +
                ", duration=" + durationMinutes +
                ", status='" + status + '\'' + '}';
    }
}