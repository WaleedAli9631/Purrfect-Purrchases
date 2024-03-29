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
import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import okhttp3.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AdoptionIntegrationTests {
    public Connection connection;
    public Javalin app;


    @BeforeEach
    public void setup() throws SQLException, IOException{
        connection = ConnectionUtility.getConnection();
        ConnectionUtility.createAdoptionOrderH2DB(connection);
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
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            String now = dtf.format(LocalDateTime.now());
            Map<String, Object> requestJson = new HashMap<>();
            requestJson.put("user_id","1");
            requestJson.put("cat_id","2");
            requestJson.put("adoption_date",now);
            System.out.println( requestJson);
            Response response = client.post("/adoptionorders", requestJson);
            int actualResponseStatusCode = response.code();
            String responseBodyJson = response.body().string();
            assertThat(actualResponseStatusCode).isEqualTo(201);
            assertThat(responseBodyJson).isEqualTo("{\"adoptionID\":1,\"catID\":2,\"adoptionDate\":\"11/28/2022\",\"userid\":\"1\"}");
        });
    }

}
