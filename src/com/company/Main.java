package com.company;

import com.company.controllers.*;
import com.company.controllers.interfaces.*;
import com.company.data.PostgresDB;
import com.company.data.interfaces.IDB;
import com.company.repositories.*;
import com.company.repositories.interfaces.*;

public class Main {
    public static void main(String[] args) {

        IDB db = new PostgresDB(
                "jdbc:postgresql://localhost:5432/somedb",
                "postgres",
                "Dilnaz2007"
        );

        // -------- Repositories --------
        IUserRepository userRepo = new UserRepository(db);
        IDoctorRepository doctorRepo = new DoctorRepository(db);
        IDoctorAvailabilityRepository availabilityRepo = new DoctorAvailabilityRepository(db);
        IAppointmentRepository appointmentRepo = new AppointmentRepository(db);

        // -------- Controllers --------
        IUserController userController = new UserController(userRepo);
        IDoctorController doctorController = new DoctorController(doctorRepo);
        IDoctorAvailabilityController availabilityController = new DoctorAvailabilityController(availabilityRepo);
        IAppointmentController appointmentController = new AppointmentController(appointmentRepo);

        // -------- Application --------
        MyApplication app = new MyApplication(
                userController,
                doctorController,
                availabilityController,
                appointmentController
        );

        app.start();
        db.close();
    }
}