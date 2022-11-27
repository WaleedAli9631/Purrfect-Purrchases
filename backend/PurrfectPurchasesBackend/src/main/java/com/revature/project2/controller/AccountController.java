package com.revature.project2.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.gson.Gson;
import com.revature.project2.model.Account;
import com.revature.project2.service.AccountService;
import io.javalin.Javalin;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.Map;

public class AccountController {
    private final AccountService accountService = new AccountService();

    public void mapEndpoints(Javalin app) {
        app.post("/accounts/{accountString}", (context -> {
            String[] accountInfo = new Gson().fromJson(context.pathParam("accountString"), String[].class);
            try {
                FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(accountInfo[6]);
                Account createdAccount = accountService.addAccount(accountInfo[0], accountInfo[1], accountInfo[2], accountInfo[3], accountInfo[4], accountInfo[5],accountInfo[7]);
                context.status(201);
                context.json(createdAccount);
            } catch (FirebaseAuthException e) {
                context.status(400);
            }
        }));

        app.get("/accounts/{uidtoken}", context -> {
            String[] accountInfo = new Gson().fromJson(context.pathParam("uidtoken"), String[].class);
            try {
                FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(accountInfo[1]);
                Account account = accountService.getAccountByUID(accountInfo[0]);
                context.json(account);
            } catch (FirebaseAuthException e) {
                context.status(400);
            }

        });
        app.put("/accounts/{accountString}", (context -> {
            String[] accountInfo = new Gson().fromJson(context.pathParam("accountString"), String[].class);
            try {
                FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(accountInfo[6]);
                Account updatedAccount = accountService.updateAccount(accountInfo[0], accountInfo[1], accountInfo[2], accountInfo[3], accountInfo[4], accountInfo[5], accountInfo[7]);
                context.status(201);
                context.json(updatedAccount);
            } catch (FirebaseAuthException e) {
                context.status(400);
            }

        }));
    }
}
