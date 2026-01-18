package com.company.models;

public class Doctor {
    private int id;
    private String name;
    private String surname;
    private boolean gender;
    private String position;

    public Doctor() {}

    public Doctor(String name, String surname, boolean gender, String position) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.position = position;
    }

    public Doctor(int id, String name, String surname, boolean gender, String position) {
        this(name, surname, gender, position);
        this.id = id;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getSurname() { return surname; }
    public boolean getGender() { return gender; }
    public String getPosition() { return position; }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", gender=" + (gender ? "Male" : "Female") +
                ", position='" + position + '\'' +
                '}';
    }
}