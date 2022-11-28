package com.revature.project2.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.gson.Gson;
import com.revature.project2.Main;
import com.revature.project2.dto.Message;
import com.revature.project2.model.Account;
import com.revature.project2.service.AccountService;
import io.javalin.Javalin;
import org.apache.commons.lang3.ObjectUtils;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

public class AccountController {
    private final AccountService accountService = new AccountService();

    public void mapEndpoints(Javalin app) {
        InputStream props = Main.class.getClassLoader().getResourceAsStream("database_config.properties");
        Properties properties = new Properties();
        try {
            properties.load(props);
        } catch (Exception e) {
            System.out.println(e);
        }

        boolean useH2 = Boolean.parseBoolean(properties.getProperty("use-h2"));

        app.post("/accounts/{accountString}", (context -> {
            String[] accountInfo = new Gson().fromJson(context.pathParam("accountString"), String[].class);
            try {
                if (!useH2) {
                    FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(accountInfo[6]);
                }
                Account createdAccount = accountService.addAccount(accountInfo[0], accountInfo[1], accountInfo[2], accountInfo[3], accountInfo[4], accountInfo[5], accountInfo[7]);
                context.status(201);
                context.json(createdAccount);
            } catch (Exception e) {
                context.status(400);
                context.json(new Message("Invalid new account info or auth token"));

            }
        }));

        app.get("/accounts/{uidtoken}", context -> {
            String[] accountInfo = new Gson().fromJson(context.pathParam("uidtoken"), String[].class);
            try {
                if (!useH2) {
                    FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(accountInfo[1]);
                }
                Account account = accountService.getAccountByUID(accountInfo[0]);
                context.status(200);
                context.json(account);
            } catch (Exception e) {
                context.status(400);
                context.json(new Message("Invalid UID or auth token"));
            }

        });
        app.put("/accounts/{accountString}", (context -> {
            String[] accountInfo = new Gson().fromJson(context.pathParam("accountString"), String[].class);
            try {
                if (!useH2) {
                    FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(accountInfo[6]);
                }
                Account updatedAccount = accountService.updateAccount(accountInfo[0], accountInfo[1], accountInfo[2], accountInfo[3], accountInfo[4], accountInfo[5], accountInfo[7]);
                context.status(200);
                context.json(updatedAccount);
            } catch (Exception e) {
                context.status(400);
                context.json(new Message("Invalid updated account info or auth token"));

            }

        }));
    }
}
