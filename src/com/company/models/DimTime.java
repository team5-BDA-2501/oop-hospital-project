package com.company.models;

import java.time.LocalDate;

public class DimTime {
    public int id;
    public LocalDate date;

    public DimTime(int id, LocalDate date) {
        this.id = id;
        this.date = date;
    }
}