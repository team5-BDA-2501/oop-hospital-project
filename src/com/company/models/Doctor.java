package com.company.models;

public class Doctor {
    private int id;
    private String firstName;
    private String lastName;
    private String specialization;
    private String email;
    private String phone;
    private boolean isActive;

    public Doctor(int id, String firstName, String lastName, String specialization, String email, String phone, boolean isActive) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialization = specialization;
        this.email = email;
        this.phone = phone;
        this.isActive = isActive;
    }

    // Getters and Setters
    public int getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getSpecialization() { return specialization; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public boolean isActive() { return isActive; }
}