package com.company.repositories.interfaces;

import java.time.LocalDate;

public interface IDimTimeRepository {
    int getOrCreateTimeId(LocalDate date);
}
