package com.company.controllers.interfaces;

import com.company.models.DoctorAvailability;
import java.util.List;

public interface IDoctorAvailabilityController {
    List<DoctorAvailability> getDoctorAvailability(int doctorId);

    boolean addDoctorAvailability(int doctorId, String dayOfWeek, String startTime, String endTime);
}