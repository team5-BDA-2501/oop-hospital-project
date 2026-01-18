package com.company;

import com.company.controllers.DoctorController;
import com.company.controllers.UserController;
import com.company.controllers.interfaces.IDoctorController;
import com.company.controllers.interfaces.IUserController;
import com.company.data.PostgresDB;
import com.company.data.interfaces.IDB;
import com.company.repositories.DoctorRepository;
import com.company.repositories.UserRepository;
import com.company.repositories.interfaces.IDoctorRepository;
import com.company.repositories.interfaces.IUserRepository;

public class Main {
    public static void main(String[] args) {
        IDB db = new PostgresDB("jdbc:postgresql://localhost:5432", "postgres", "Dilnaz2007", "somedb");

        IUserRepository userRepo = new UserRepository(db);
        IUserController userController = new UserController(userRepo);

        IDoctorRepository doctorRepo = new DoctorRepository(db);
        IDoctorController doctorController = new DoctorController(doctorRepo);

        MyApplication app = new MyApplication(userController, doctorController);
        app.start();

        db.close();
    }
}