package com.revature.project2.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.gson.Gson;
import com.revature.project2.dto.AllCatInformation;
import com.revature.project2.dto.CatInformation;
import com.revature.project2.model.Cat;
import com.revature.project2.service.CatService;
import io.javalin.Javalin;
import org.json.JSONObject;

import java.util.List;

public class CatController {
    private CatService catService = new CatService();
    public void mapEndpoints(Javalin app) {
        app.get("/cat/{catid}", (ctx) -> {
            //CatInformation credentials = ctx.bodyAsClass(CatInformation.class);
            String theID = ctx.pathParam("catid");
            Cat cat = catService.getCat(Integer.parseInt(theID));

            ctx.json(cat);
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
            System.out.println(jsonString);
            JSONObject obj = new JSONObject(jsonString);
            String breed = obj.getString("breed");
            int age = obj.getInt("age");
            String gender = obj.getString("gender");
            List<Cat> cat = catService.getCatList(breed, age, gender);

            //AllCatInformation info = ctx.bodyAsClass(AllCatInformation.class);
            //List<Cat> cat = catService.getCatList(info);

            ctx.json(cat);
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
