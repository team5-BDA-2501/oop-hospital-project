package com.company.models;

public class Doctor {

    private int id;
    private String firstName;
    private String lastName;
    private String specialization;
    private String email;
    private String phone;
    private boolean isActive;

    public Doctor(String firstName, String lastName, String specialization,
                  String email, String phone, boolean isActive) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialization = specialization;
        this.email = email;
        this.phone = phone;
        this.isActive = isActive;
    }

    public Doctor(int id, String firstName, String lastName, String specialization,
                  String email, String phone, boolean isActive) {
        this(firstName, lastName, specialization, email, phone, isActive);
        this.id = id;
    }

    public int getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getSpecialization() { return specialization; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public boolean isActive() { return isActive; }

    @Override
    public String toString() {
        return id + " | " + firstName + " " + lastName +
                " | " + specialization + " | " +
                (isActive ? "Active" : "Inactive");
    }
}