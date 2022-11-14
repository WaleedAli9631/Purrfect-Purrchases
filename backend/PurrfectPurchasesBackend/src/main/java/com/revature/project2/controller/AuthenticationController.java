package com.revature.project2.controller;

import com.revature.project2.dto.CatInformation;
import com.revature.project2.model.Cat;
import com.revature.project2.service.CatService;
import io.javalin.Javalin;

import java.util.List;

public class AuthenticationController {
    private CatService catService = new CatService();
    public void mapEndpoints(Javalin app) {
        app.post("/getcatinfo", (ctx) -> {
            CatInformation credentials = ctx.bodyAsClass(CatInformation.class);
            Cat cat = catService.getCat(credentials.getCatID());

            ctx.json(cat);
        });
        app.post("/getallcats", (ctx) -> {
            List<Cat> cat = catService.getCatList();

            ctx.json(cat);
        });
    }
}
