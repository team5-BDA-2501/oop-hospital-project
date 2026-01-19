package com.company.models;

public class User {

    private int id;
    private String name;
    private String surname;
    private boolean gender;

    public User(String name, String surname, boolean gender) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
    }

    public User(int id, String name, String surname, boolean gender) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getSurname() { return surname; }
    public boolean getGender() { return gender; }

    @Override
    public String toString() {
        return id + " | " + name + " " + surname +
                " | " + (gender ? "Male" : "Female");
    }
}