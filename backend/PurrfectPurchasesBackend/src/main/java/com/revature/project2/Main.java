package com.revature.project2;


import com.revature.project2.controller.AccountController;
import com.revature.project2.controller.AdoptionOrderController;
import com.revature.project2.controller.CatController;


import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create((config)-> {
            config.plugins.enableCors((cors)->{
                cors.add(it -> {
                    it.defaultScheme = "http";
                    it.allowHost("127.0.0.1:5500");
                    it.allowCredentials = true;
                });
            });
        });

        AccountController accountController= new AccountController();
        accountController.mapEndpoints(app);

        CatController authController = new CatController();
        authController.mapEndpoints(app);

        AdoptionOrderController adoptionOrderController = new AdoptionOrderController();
        adoptionOrderController.mapEndpoints(app);

        app.start(9090);        //Why doesn't port 8080 work?


    }
}
