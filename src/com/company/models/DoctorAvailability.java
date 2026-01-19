package com.company.models;

import java.time.LocalTime;

public class DoctorAvailability {
    private int id;
    private int doctorId;
    private String dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;

    public DoctorAvailability(int id, int doctorId,
                              String dayOfWeek,
                              LocalTime startTime,
                              LocalTime endTime) {
        this.id = id;
        this.doctorId = doctorId;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public DoctorAvailability(int doctorId, String dayOfWeek,
                              LocalTime startTime, LocalTime endTime) {
        this(0, doctorId, dayOfWeek, startTime, endTime);
    }

    public int getId() { return id; }
    public int getDoctorId() { return doctorId; }
    public String getDayOfWeek() { return dayOfWeek; }
    public LocalTime getStartTime() { return startTime; }
    public LocalTime getEndTime() { return endTime; }

    @Override
    public String toString() {
        return "Availability{id=" + id +
                ", doctorId=" + doctorId +
                ", day=" + dayOfWeek +
                ", " + startTime + "-" + endTime + '}';
    }
}