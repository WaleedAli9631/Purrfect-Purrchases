package com.revature.project2.controller;

import com.revature.project2.dto.AdoptionInformation;
import com.revature.project2.model.AdoptionOrder;
import com.revature.project2.service.AdoptionOrdersService;
import com.revature.project2.service.WrongDateException;
import io.javalin.Javalin;
import org.postgresql.util.PSQLException;

import java.sql.SQLException;

public class AdoptionOrderController {
    private AdoptionOrdersService adoptionOrdersService = new AdoptionOrdersService();
    public void mapEndpoints(Javalin app){

        app.post("/adoptionorders", context -> {
            AdoptionInformation adoptionInformation = context.bodyAsClass(AdoptionInformation.class);
            try {
                AdoptionOrder adoptionOrder = adoptionOrdersService.createAdoptionOrder(adoptionInformation.getUser_id(),
                        adoptionInformation.getCat_id(), adoptionInformation.getAdoption_date());

                context.status(201); //created
                context.json(adoptionOrder);
            }
            catch (SQLException e) {
                e.printStackTrace();
                if(e.getMessage().contains("ERROR: duplicate key value violates " + "unique constraint " +
                        "\"adoption_orders_cat_id_key\"")) {
                    context.status(400);
                    context.result("This cat has already been adopted");
                }
                else if (e.getMessage().contains("ERROR: insert or update on table \"adoption_orders\" violates " +
                        "foreign key constraint \"constraint_fk\"" )) {
                    context.status(400);
                    context.result("This cat does not exist");
                }
            }
            catch(WrongDateException wde) {
                context.status(400);
                context.result(wde.getMessage());
            }

        });
    }
}
