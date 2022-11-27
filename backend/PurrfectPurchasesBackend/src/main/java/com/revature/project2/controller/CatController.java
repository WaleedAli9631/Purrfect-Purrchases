package com.revature.project2.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.gson.Gson;
import com.revature.project2.dto.AllCatInformation;
import com.revature.project2.dto.CatInformation;
import com.revature.project2.model.Account;
import com.revature.project2.model.Cat;
import com.revature.project2.service.CatService;
import com.revature.project2.service.AccountService;
import io.javalin.Javalin;
import org.json.JSONObject;

import java.sql.SQLOutput;
import java.util.List;

public class CatController {
    private CatService catService = new CatService();
    private CatValidation catValidation = new CatValidation();

    private AccountService accountService = new AccountService();

    public void mapEndpoints(Javalin app) {
        app.get("/cat/{catid}", (ctx) -> {
            if (!catValidation.catIDNotInt(ctx.pathParam("catid"))) {
                ctx.result("The Cat ID was not an integer!");
                ctx.status(400);
            } else if (!catValidation.catIDMoreThanZero(ctx.pathParam("catid"))) {
                ctx.result("The Cat ID is less than one");
                ctx.status(400);
            } else {
                Cat cat = catService.getCat(Integer.parseInt(ctx.pathParam("catid")));
                if (cat == null) ctx.result("Cat ID does not exist!");
                else ctx.json(cat);
            }
        });

        app.delete("/cat/",(ctx) -> {
            CatInformation info = ctx.bodyAsClass(CatInformation.class);
            Account account = accountService.getAccountByUID(info.getUserID());
            if (account.getRole().equals("admin")) {
                catService.deleteCat(info.getCatID());
            } else {
                ctx.result("The user does not have permission to delete cats!");
                ctx.status(401);
            }
        });

        app.post("/cat", (ctx) -> {
            CatInformation info = ctx.bodyAsClass(CatInformation.class);
            Account account = accountService.getAccountByUID(info.getUserID());
            if (account.getRole().equals("admin")) {
                Cat cat = catService.addCat(info);
                ctx.json(cat);
            } else {
                ctx.result("The user does not have permission to add cats!");
                ctx.status(401);
            }
        });

        app.put("/purchase", (ctx) -> {
            CatInformation info = ctx.bodyAsClass(CatInformation.class);
            int catID = info.getCatID();
            String purchaseBy = info.getPurchasedBy();
            catService.purchaseCat(catID,purchaseBy);
        });

        app.put("/cat/", (ctx)-> {
            CatInformation info = ctx.bodyAsClass(CatInformation.class);
            Account account = accountService.getAccountByUID(info.getUserID());
            //if (account.getRole().equals("admin")) {
                Cat cat = catService.editCat(info);
                ctx.json(cat);
            /*} else {
                ctx.result("The user does not have permission to edit cats!");
                ctx.status(401);
            }*/
        });

        app.get("/allcats/{catinfo}", (ctx) -> {
            String jsonString = ctx.pathParam("catinfo");
            JSONObject obj = new JSONObject(jsonString);
            if (!obj.has("age")) {
                ctx.result("The cat's age was not included!");
                ctx.status(400);
            } else if (!obj.has("breed")) {
                ctx.result("The cat's breed was not included!");
                ctx.status(400);
            } else if (!obj.has("gender")) {
                ctx.result("The cat's gender was not included!");
                ctx.status(400);
            } else if (!catValidation.catBreedExists(obj.getString("breed"))) {
                ctx.result("The Cat Breed does not exist");
                ctx.status(400);
            } else if (!catValidation.catAgeInBoundaries(obj.get("age").toString())) {
                ctx.result("The Cat Age was not an integer!");
                ctx.status(400);
            } else if (!catValidation.catAgePositive(obj.get("age").toString())) {
                ctx.result("The Cat Age is less than zero OR more than 20");
                ctx.status(400);
            } else if (!catValidation.catGenderExists(obj.getString("gender"))){
                ctx.result("The Cat Gender does not exist");
                ctx.status(400);
            } else {
                String breed = obj.getString("breed");
                int age = obj.getInt("age");
                String gender = obj.getString("gender");

                List<Cat> cat = catService.getCatList(breed, age, gender);
                if (cat == null) ctx.result("Cat with filter information does not exist!");
                ctx.json(cat);
            }
        });
        app.get("/usercats/{userinfo}", (ctx) -> {
            String[] accountInfo = new Gson().fromJson(ctx.pathParam("userinfo"), String[].class);
            try {
                FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(accountInfo[1]);
                List<Cat> cat = catService.getUserAdoptedCats(accountInfo[0]);
                ctx.json(cat);
            } catch (FirebaseAuthException e) {
                ctx.status(400);
            }

        });
    }

}
