package com.company.repositories.interfaces;

import com.company.models.DoctorAvailability;
import java.util.List;

public interface IDoctorAvailabilityRepository {
    List<DoctorAvailability> getAvailabilityByDoctor(int doctorId);
    boolean addAvailability(int doctorId, String dayOfWeek, String startTime, String endTime);
}