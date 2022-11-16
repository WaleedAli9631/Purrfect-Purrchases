package com.revature.project2.controller;

import com.google.gson.Gson;
import com.revature.project2.model.Account;
import com.revature.project2.service.AccountService;
import io.javalin.Javalin;

import java.util.Map;

public class AccountController {
    private AccountService accountService = new AccountService();

    public void mapEndpoints(Javalin app) {
        app.post("/accounts/{accountString}", (context -> {
            String[] accountInfo = new Gson().fromJson(context.pathParam("accountString"), String[].class);
            Account createdAccount = accountService.addAccount(accountInfo[0],accountInfo[1],accountInfo[2],accountInfo[3],accountInfo[4],accountInfo[5]);

            context.status(201);
            context.json(createdAccount);
        }));

        app.get("/accounts/{uid}", context -> {
            Account account = accountService.getAccountByUID(context.pathParam("uid"));
            context.json(account);
        });
    }
}
