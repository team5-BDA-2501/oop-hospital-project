package com.company.models;

public class Doctor {
    private int id;
    private String firstName;
    private String lastName;
    private String specialization;
    private String email;
    private String phone;
    private boolean isActive;
    private String username;


    public Doctor(String firstName, String lastName, String specialization, String email, String phone, boolean isActive, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialization = specialization;
        this.email = email;
        this.phone = phone;
        this.isActive = isActive;
        this.username = username;
    }


    public Doctor(int id, String firstName, String lastName, String specialization, String email, String phone, boolean isActive) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialization = specialization;
        this.email = email;
        this.phone = phone;
        this.isActive = isActive;
    }


    public int getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getSpecialization() { return specialization; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public boolean isActive() { return isActive; }
    public String getUsername() { return username; }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", specialization='" + specialization + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}