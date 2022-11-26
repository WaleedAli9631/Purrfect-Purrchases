package com.revature.project2.controller;

import com.revature.project2.dto.AllCatInformation;
import com.revature.project2.dto.CatInformation;
import com.revature.project2.model.Cat;
import com.revature.project2.service.CatService;
import io.javalin.Javalin;
import org.json.JSONObject;

import java.util.List;

public class CatController {
    private CatService catService = new CatService();
    private CatValidation catValidation = new CatValidation();

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

        app.delete("/cat/{catid}",(ctx) -> {
            String theID = ctx.pathParam("catid");
            catService.deleteCat(Integer.parseInt(theID));
        });
        app.post("/cat", (ctx) -> {
            CatInformation info = ctx.bodyAsClass(CatInformation.class);
            Cat cat = catService.addCat(info);
            ctx.json(cat);
        });

        app.put("/cat/", (ctx)-> {
            CatInformation info = ctx.bodyAsClass(CatInformation.class);
            Cat cat = catService.editCat(info);
            ctx.json(cat);
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
            } else if (!catValidation.catAgeInBoundaries(obj.getString("age"))) {
                ctx.result("The Cat Age was not an integer!");
                ctx.status(400);
            } else if (!catValidation.catAgePositive(obj.getString("age"))) {
                ctx.result("The Cat Age is less than zero OR more than 20");
                ctx.status(400);
            } else if (!catValidation.catBreedExists(obj.getString("breed"))) {
                ctx.result("The Cat Breed does not exist");
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
    }
}
