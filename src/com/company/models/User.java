package com.company.models;

public class User {

    private int id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private boolean gender;
    private Role role;          // enum
    private Integer doctorId;   // nullable (can be null)


    public User(int id,
                String name,
                String surname,
                String username,
                String password,
                boolean gender,
                Role role,
                Integer doctorId) {

        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.role = role;
        this.doctorId = doctorId;
    }


    public User(int id,
                String name,
                String surname,
                String username,
                String password,
                boolean gender,
                Role role) {

        this(id, name, surname, username, password, gender, role, null);
    }


    public User(String name,
                String surname,
                String username,
                String password,
                boolean gender) {

        this(0, name, surname, username, password, gender, Role.USER, null);
    }


    public User(String name,
                String surname,
                String username,
                String password,
                boolean gender,
                Role role,
                Integer doctorId) {

        this(0, name, surname, username, password, gender, role, doctorId);
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getSurname() { return surname; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public boolean getGender() { return gender; }

    public Role getRole() { return role; }
    public Integer getDoctorId() { return doctorId; }
}