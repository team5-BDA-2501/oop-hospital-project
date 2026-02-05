package com.company;

import com.company.factory.AppFactory;

public class Main {
    public static void main(String[] args) {
        AppFactory.Bundle b = AppFactory.build();

        MyApplication app = new MyApplication(
                b.userController,
                b.appointmentController,
                b.doctorController,
                b.adminController
        );
        app.start();
    }
}