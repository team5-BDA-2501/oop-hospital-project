package com.company.controllers;

import com.company.controllers.interfaces.IDoctorAvailabilityController;
import com.company.models.DoctorAvailability;
import com.company.repositories.interfaces.IDoctorAvailabilityRepository;

import java.util.List;

public class DoctorAvailabilityController implements IDoctorAvailabilityController {

    private final IDoctorAvailabilityRepository availabilityRepo;

    public DoctorAvailabilityController(IDoctorAvailabilityRepository availabilityRepo) {
        this.availabilityRepo = availabilityRepo;
    }

    @Override
    public List<DoctorAvailability> getDoctorAvailability(int doctorId) {
        return availabilityRepo.getAvailabilityByDoctor(doctorId);
    }

    @Override
    public boolean addDoctorAvailability(int doctorId, String dayOfWeek, String startTime, String endTime) {
        return availabilityRepo.addAvailability(doctorId, dayOfWeek, startTime, endTime);
    }

    public void viewAvailableSlots(int doctorId) {
        List<DoctorAvailability> slots = getDoctorAvailability(doctorId);
        if (slots.isEmpty()) {
            System.out.println("No available slots for this doctor.");
        } else {
            System.out.println("Available slots for Doctor ID " + doctorId + ":");
            for (DoctorAvailability slot : slots) {
                System.out.println("Day: " + slot.getDayOfWeek() + " | Start: " + slot.getStartTime() + " | End: " + slot.getEndTime());
            }
        }
    }
}