package com.company;

import com.company.data.PostgresDB;
import com.company.repositories.UserRepository;
import com.company.repositories.AppointmentRepository;
import com.company.repositories.DoctorRepository;
import com.company.repositories.DoctorAvailabilityRepository;
import com.company.controllers.UserController;
import com.company.controllers.AppointmentController;
import com.company.controllers.DoctorController;
import com.company.controllers.DoctorAvailabilityController;

public class Main {
    public static void main(String[] args) {
        PostgresDB db = new PostgresDB();

        UserRepository userRepository = new UserRepository(db.getConnection());
        AppointmentRepository appointmentRepository = new AppointmentRepository(db);
        DoctorRepository doctorRepository = new DoctorRepository(db.getConnection());
        DoctorAvailabilityRepository doctorAvailabilityRepository = new DoctorAvailabilityRepository(db.getConnection());


        UserController userController = new UserController(userRepository);
        AppointmentController appointmentController = new AppointmentController(appointmentRepository);
        DoctorController doctorController = new DoctorController(doctorRepository);
        DoctorAvailabilityController doctorAvailabilityController = new DoctorAvailabilityController(doctorAvailabilityRepository);


        MyApplication app = new MyApplication(userController, appointmentController, doctorController, doctorAvailabilityController);
        app.start();
    }
}