package com.revature.integration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.revature.project2.controller.AccountController;
import com.revature.project2.controller.CatController;
import com.revature.project2.dao.ConnectionUtility;
import io.cucumber.cienvironment.internal.com.eclipsesource.json.Json;
import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonArray;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import okhttp3.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class CatIntegrationTests {
    public Connection connection;
    public Javalin app;


    @BeforeEach
    public void setup() throws SQLException, IOException {
        FileInputStream serviceAccount =
                new FileInputStream("C:/Users/PcName/Downloads/purrfect-purrchases-firebase-adminsdk-sryyb-780fda786b.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        FirebaseApp.initializeApp(options);
        connection = ConnectionUtility.getConnection();
        ConnectionUtility.populateH2Database(connection);

        app = Javalin.create();

        CatController catController = new CatController();
        catController.mapEndpoints(app);

    }

    @AfterEach
    public void clearDb() throws SQLException, IOException {
        FirebaseApp.getInstance().delete();
        ConnectionUtility.clearH2Database(connection);
        connection.close();
    }

    @Test
    public void getUserCatPositive() {
        JavalinTest.test(app, (server, client) -> {
            JsonArray jsonArray = Json.array();
            jsonArray.add("PJuTs9hFfvfMNVlWmR17t3LYEVN2");
            jsonArray.add(FirebaseAuth.getInstance().createCustomToken("PJuTs9hFfvfMNVlWmR17t3LYEVN2"));
            Response response = client.get("/usercats/"+jsonArray);
            int actualResponseStatusCode = response.code();
            String responseBodyJson = response.body().string();

            assertThat(actualResponseStatusCode).isEqualTo(200);
            assertThat(responseBodyJson).isEqualTo("[{\"id\":1,\"name\":\"Kitty Kitty Bang Bang\",\"breed\":\"Ragdoll\",\"gender\":\"Male\",\"color\":\"yellow\",\"age\":4,\"imageFile\":\"assets/img/cat13.jpg\",\"costs\":60,\"purchasedBy\":null}]");
        });
    }

    @Test
    public void getUserCatNegative() {
        JavalinTest.test(app, (server, client) -> {
            JsonArray jsonArray = Json.array();
            jsonArray.add("PJuTs9hFfvfMNVlWmR17t3LYEVN2111111");
            jsonArray.add(FirebaseAuth.getInstance().createCustomToken("PJuTs9hFfvfMNVlWmR17t3LYEVN2"));
            Response response = client.get("/usercats/"+jsonArray);
            int actualResponseStatusCode = response.code();
            String responseBodyJson = response.body().string();
            Assert.assertEquals("[]",responseBodyJson);
        });
    }
}
