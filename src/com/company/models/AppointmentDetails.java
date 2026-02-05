package com.company.models;

import java.time.LocalDateTime;


public class AppointmentDetails {
    private int appointmentId;
    private String userFullName;
    private String doctorFullName;
    private String doctorSpecialization;
    private LocalDateTime appointmentDatetime;
    private String status;

    public AppointmentDetails(int appointmentId, String userFullName, String doctorFullName,
                              String doctorSpecialization, LocalDateTime appointmentDatetime, String status) {
        this.appointmentId = appointmentId;
        this.userFullName = userFullName;
        this.doctorFullName = doctorFullName;
        this.doctorSpecialization = doctorSpecialization;
        this.appointmentDatetime = appointmentDatetime;
        this.status = status;
    }

    @Override
    public String toString() {
        return "AppointmentDetails{" +
                "appointmentId=" + appointmentId +
                ", user='" + userFullName + '\'' +
                ", doctor='" + doctorFullName + '\'' +
                ", specialization='" + doctorSpecialization + '\'' +
                ", datetime=" + appointmentDatetime +
                ", status='" + status + '\'' +
                '}';
    }
}