package com.revature.integration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.revature.project2.controller.AccountController;
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

public class AccountIntegrationTests {
    public Connection connection;
    public Javalin app;


    @BeforeEach
    public void setup() throws SQLException, IOException{
        FileInputStream serviceAccount =
                new FileInputStream("C:/Users/wutan/Downloads/purrfect-purrchases-firebase-adminsdk-sryyb-780fda786b.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        FirebaseApp.initializeApp(options);
        connection = ConnectionUtility.getConnection();
        ConnectionUtility.populateH2Database(connection);

        app = Javalin.create();

        AccountController accountController = new AccountController();
        accountController.mapEndpoints(app);

    }

    @AfterEach
    public void clearDb() throws SQLException, IOException {
        FirebaseApp.getInstance().delete();
        ConnectionUtility.clearH2Database(connection);
        connection.close();
    }

    @Test
    public void getUserPositive() {
        JavalinTest.test(app, (server, client) -> {
            JsonArray jsonArray = Json.array();
            jsonArray.add("PJuTs9hFfvfMNVlWmR17t3LYEVN2");
            jsonArray.add(FirebaseAuth.getInstance().createCustomToken("PJuTs9hFfvfMNVlWmR17t3LYEVN2"));
            Response response = client.get("/accounts/"+jsonArray);
            int actualResponseStatusCode = response.code();
            String responseBodyJson = response.body().string();

            assertThat(actualResponseStatusCode).isEqualTo(200);
            assertThat(responseBodyJson).isEqualTo("{\"uid\":\"PJuTs9hFfvfMNVlWmR17t3LYEVN2\",\"fname\":\"fakeFname1\",\"lname\":\"fakeLname1\",\"streetAddress\":\"fake street1\",\"city\":\"fake city1\",\"state\":\"fake state1\",\"role\":null}");
        });
    }

    @Test
    public void getUserNegative() {
        JavalinTest.test(app, (server, client) -> {
            JsonArray jsonArray = Json.array();
            jsonArray.add("PJuTs9hFfvfMNVlWmR17t3LYEVN2111111");
            jsonArray.add(FirebaseAuth.getInstance().createCustomToken("PJuTs9hFfvfMNVlWmR17t3LYEVN2"));
            Response response = client.get("/accounts/"+jsonArray);
            int actualResponseStatusCode = response.code();
            String responseBodyJson = response.body().string();

            assertThat(actualResponseStatusCode).isEqualTo(400);
            assertThat(responseBodyJson).isEqualTo("{\"message\":\"Invalid UID or auth token\"}");
        });
    }

    @Test
    public void createUserPositive() {
        JavalinTest.test(app, (server, client) -> {
            JsonArray jsonArray = Json.array();
            jsonArray.add("SpGPizs8CcVxn5aheXGtFMtjeCi2");
            jsonArray.add("fakeFname4");
            jsonArray.add("fakeLname4");
            jsonArray.add("fake street4");
            jsonArray.add("fake city4");
            jsonArray.add("fake state4");
            jsonArray.add(FirebaseAuth.getInstance().createCustomToken("SpGPizs8CcVxn5aheXGtFMtjeCi2"));
            jsonArray.add("admin");

            Response response = client.post("/accounts/"+jsonArray);
            int actualResponseStatusCode = response.code();
            String responseBodyJson = response.body().string();

            assertThat(actualResponseStatusCode).isEqualTo(201);
            assertThat(responseBodyJson).isEqualTo("{\"uid\":\"SpGPizs8CcVxn5aheXGtFMtjeCi2\",\"fname\":\"fakeFname4\",\"lname\":\"fakeLname4\",\"streetAddress\":\"fake street4\",\"city\":\"fake city4\",\"state\":\"fake state4\",\"role\":\"admin\"}");
        });
    }

    @Test
    public void createUserNegative() {
        JavalinTest.test(app, (server, client) -> {
            JsonArray jsonArray = Json.array();
            jsonArray.add("fake street4");
            jsonArray.add("fake city4");
            jsonArray.add("fake state4");
            jsonArray.add(FirebaseAuth.getInstance().createCustomToken("PJuTs9hFfvfMNVlWmR17t3LYEVN2"));
            jsonArray.add("admin");

            Response response = client.post("/accounts/"+jsonArray);
            int actualResponseStatusCode = response.code();
            String responseBodyJson = response.body().string();

            assertThat(actualResponseStatusCode).isEqualTo(400);
            assertThat(responseBodyJson).isEqualTo("{\"message\":\"Invalid new account info or auth token\"}");
        });
    }

    @Test
    public void updateUserPositive() {
        JavalinTest.test(app, (server, client) -> {
            JsonArray jsonArray = Json.array();
            jsonArray.add("PJuTs9hFfvfMNVlWmR17t3LYEVN2");
            jsonArray.add("fakeFname4");
            jsonArray.add("fakeLname4");
            jsonArray.add("fake street4");
            jsonArray.add("fake city4");
            jsonArray.add("fake state4");
            jsonArray.add(FirebaseAuth.getInstance().createCustomToken("PJuTs9hFfvfMNVlWmR17t3LYEVN2"));
            jsonArray.add("admin");

            Response response = client.put("/accounts/"+jsonArray);
            int actualResponseStatusCode = response.code();
            String responseBodyJson = response.body().string();

            assertThat(actualResponseStatusCode).isEqualTo(200);
            assertThat(responseBodyJson).isEqualTo("{\"uid\":\"PJuTs9hFfvfMNVlWmR17t3LYEVN2\",\"fname\":\"fakeFname4\",\"lname\":\"fakeLname4\",\"streetAddress\":\"fake street4\",\"city\":\"fake city4\",\"state\":\"fake state4\",\"role\":null}");
        });
    }

    @Test
    public void updateUserNegative() {
        JavalinTest.test(app, (server, client) -> {
            JsonArray jsonArray = Json.array();
            jsonArray.add("fake street4");
            jsonArray.add("fake city4");
            jsonArray.add("fake state4");
            jsonArray.add(FirebaseAuth.getInstance().createCustomToken("PJuTs9hFfvfMNVlWmR17t3LYEVN2"));
            jsonArray.add("admin");

            Response response = client.put("/accounts/"+jsonArray);
            int actualResponseStatusCode = response.code();
            String responseBodyJson = response.body().string();

            assertThat(actualResponseStatusCode).isEqualTo(400);
            assertThat(responseBodyJson).isEqualTo("{\"message\":\"Invalid updated account info or auth token\"}");
        });
    }
}
