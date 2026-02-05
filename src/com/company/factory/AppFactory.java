package com.company.factory;

import com.company.controllers.*;
import com.company.data.PostgresDB;
import com.company.repositories.*;
import com.company.repositories.interfaces.*;

import java.sql.Connection;

public class AppFactory {

    public static class Bundle {
        public final UserController userController;
        public final AppointmentController appointmentController;
        public final DoctorController doctorController;
        public final DoctorAvailabilityController availabilityController;
        public final AdminController adminController;

        public Bundle(UserController u, AppointmentController a, DoctorController d, DoctorAvailabilityController av, AdminController admin) {
            this.userController = u;
            this.appointmentController = a;
            this.doctorController = d;
            this.availabilityController = av;
            this.adminController = admin;
        }
    }

    public static Bundle build() {
        PostgresDB db = PostgresDB.getInstance();
        Connection conn = db.getConnection();

        IUserRepository userRepo = new UserRepository(conn);
        IDoctorRepository doctorRepo = new DoctorRepository(conn);
        IAppointmentRepository appointmentRepo = new AppointmentRepository(conn);
        IDoctorAvailabilityRepository availabilityRepo = new DoctorAvailabilityRepository(conn);

        UserController userController = new UserController(userRepo);
        DoctorController doctorController = new DoctorController(doctorRepo);
        DoctorAvailabilityController availabilityController = new DoctorAvailabilityController(availabilityRepo);
        AppointmentController appointmentController = new AppointmentController(appointmentRepo);

        AdminController adminController = new AdminController(doctorRepo);

        return new Bundle(userController, appointmentController, doctorController, availabilityController, adminController);
    }
}