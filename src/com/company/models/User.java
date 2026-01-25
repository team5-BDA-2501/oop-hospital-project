package com.company.models;

public class User {
    private int id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private boolean gender;

    public User(int id, String name, String surname, String username, String password, boolean gender) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.gender = gender;
    }

    public User(String name, String surname, String username, String password, boolean gender) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.gender = gender;
    }

    // Getters and Setters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getSurname() { return surname; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public boolean getGender() { return gender; }
}