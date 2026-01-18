package com.company;

import com.company.controllers.UserController;
import com.company.controllers.DoctorController;
import com.company.controllers.interfaces.IUserController;
import com.company.controllers.interfaces.IDoctorController;
import com.company.data.PostgresDB;
import com.company.data.interfaces.IDB;
import com.company.repositories.UserRepository;
import com.company.repositories.DoctorRepository;
import com.company.repositories.interfaces.IUserRepository;
import com.company.repositories.interfaces.IDoctorRepository;

public class Main {

    public static void main(String[] args) {
        // Here you specify which DB and UserRepository to use
        // And changing DB should not affect to whole code
        IDB db = new PostgresDB("jdbc:postgresql://localhost:5432", "postgres", "0404", "hospital-db");

        IUserRepository userRepo = new UserRepository(db);
        IUserController userController = new UserController(userRepo);

        IDoctorRepository doctorRepo = new DoctorRepository(db);
        IDoctorController doctorController = new DoctorController(doctorRepo);

        MyApplication app = new MyApplication(userController);

        app.start();

        db.close();
    }
}