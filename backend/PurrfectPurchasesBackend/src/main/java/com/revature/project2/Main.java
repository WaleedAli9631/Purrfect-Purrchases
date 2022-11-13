package com.revature.project2;

import com.revature.project2.controller.AuthenticationController;
import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create();
        AuthenticationController authController = new AuthenticationController();
        authController.mapEndpoints(app);

        app.start(9090);        //Why doesn't port 8080 work?


    }
}
