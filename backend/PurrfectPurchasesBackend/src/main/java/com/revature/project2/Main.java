package com.revature.project2;

import com.revature.project2.controller.AccountController;
import com.revature.project2.controller.AuthenticationController;
import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create((config)-> {
            config.plugins.enableCors((cors)->{
                cors.add(it -> {
                    it.defaultScheme = "http";
                    it.allowHost("localhost:5500");
                    it.allowHost("127.0.0.1:5500");
                    it.allowCredentials = true;
                });
            });
        });

        AuthenticationController authController = new AuthenticationController();
        AccountController accountController= new AccountController();
        authController.mapEndpoints(app);
        accountController.mapEndpoints(app);
        app.start(9090);        //Why doesn't port 8080 work?


    }
}
