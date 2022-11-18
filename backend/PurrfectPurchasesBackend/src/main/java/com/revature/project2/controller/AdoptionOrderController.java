package com.revature.project2.controller;

import com.revature.project2.dto.AdoptionInformation;
import com.revature.project2.model.AdoptionOrder;
import com.revature.project2.service.AdoptionOrdersService;
import io.javalin.Javalin;

public class AdoptionOrderController {
    private AdoptionOrdersService adoptionOrdersService = new AdoptionOrdersService();
    public void mapEndpoints(Javalin app){

        app.post("/adoptionorders", context -> {
            //HttpSession httpSession = context.req().getSession();
            AdoptionInformation adoptionInformation = context.bodyAsClass(AdoptionInformation.class);
            AdoptionOrder adoptionOrder = adoptionOrdersService.createAdoptionOrder(adoptionInformation.getUser_id(),
                    adoptionInformation.getCat_id(), adoptionInformation.getAdoption_date());

            context.status(201); //created
            context.json(adoptionOrder);
        });
    }
}
