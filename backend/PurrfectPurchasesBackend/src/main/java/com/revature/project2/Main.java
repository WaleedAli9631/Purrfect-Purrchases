package com.revature.project2;

import com.revature.project2.controller.CatController;
import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create((config)-> {
            config.plugins.enableCors((cors)->{
                cors.add(it -> {
                    it.defaultScheme = "http";
                    it.allowHost("localhost:5501");
                    it.allowHost("127.0.0.1:5501");
                    it.allowCredentials = true;
                });
            });
        });

        CatController authController = new CatController();
        authController.mapEndpoints(app);

        app.start(9090);        //Why doesn't port 8080 work?


    }
}
