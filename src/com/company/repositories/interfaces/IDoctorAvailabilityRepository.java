package com.company.repositories.interfaces;

import com.company.models.DoctorAvailability;
import java.util.List;

public interface IDoctorAvailabilityRepository {
    boolean add(DoctorAvailability a);
    List<DoctorAvailability> getByDoctor(int doctorId);
}