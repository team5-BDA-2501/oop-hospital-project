package com.company.models;

public class Doctor {
    private int id;
    private String name;
    private String surname;
    private boolean gender;
    private String position;

    public Doctor(){

    }

    public Doctor(String name, String surname, boolean gender, String position) {
        setName(name);
        setSurname(surname);
        setGender(gender);
    }

    public Doctor(int id, String name, String surname, boolean gender, String position) {
        this(name, surname, gender, position);
        setId(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getPosition(){
        return position;
    }

    public void setPosition(String position){
        this.position = position;
    }

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
