package com.revature.integration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.revature.project2.controller.AccountController;
import com.revature.project2.controller.AdoptionOrderController;
import com.revature.project2.dao.ConnectionUtility;
import io.cucumber.cienvironment.internal.com.eclipsesource.json.Json;
import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonArray;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import okhttp3.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class AdoptionIntegrationTests {
    public Connection connection;
    public Javalin app;


    @BeforeEach
    public void setup() throws SQLException, IOException{
        connection = ConnectionUtility.getConnection();
        app = Javalin.create();
        AdoptionOrderController adoptionOrderController = new AdoptionOrderController();
        adoptionOrderController.mapEndpoints(app);
    }

    @AfterEach
    public void clearDb() throws SQLException, IOException {
        ConnectionUtility.clearH2Database(connection);
        connection.close();
    }

    @Test
    public void addOrderPositive(){
        JavalinTest.test(app, (server,client) ->{
            JsonArray jsonArray = Json.array();
            jsonArray.add("1");
            jsonArray.add("2");

        });
    }

}
