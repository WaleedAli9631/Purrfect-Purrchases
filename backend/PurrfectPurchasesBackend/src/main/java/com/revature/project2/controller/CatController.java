package com.revature.project2.controller;

import com.revature.project2.dto.AllCatInformation;
import com.revature.project2.dto.CatInformation;
import com.revature.project2.model.Cat;
import com.revature.project2.service.CatService;
import io.javalin.Javalin;

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

        app.post("/allcats", (ctx) -> {
            AllCatInformation info = ctx.bodyAsClass(AllCatInformation.class);
            List<Cat> cat = catService.getCatList(info);

            ctx.json(cat);
        });
    }
}
