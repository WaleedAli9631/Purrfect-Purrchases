package com.revature.project2;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.internal.FirebaseTokenFactory;
import com.revature.project2.controller.AccountController;
import com.revature.project2.controller.AdoptionOrderController;
import com.revature.project2.controller.CatController;


import io.javalin.Javalin;

import java.io.FileInputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        FileInputStream serviceAccount =
                new FileInputStream("C:/Users/PcName/Downloads/purrfect-purrchases-firebase-adminsdk-sryyb-780fda786b.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        FirebaseApp.initializeApp(options);

        
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
