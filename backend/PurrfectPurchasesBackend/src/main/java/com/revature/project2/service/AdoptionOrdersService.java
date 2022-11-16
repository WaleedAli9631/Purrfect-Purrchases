package com.revature.project2.service;

import com.revature.project2.dao.AdoptionOrdersDAO;
import com.revature.project2.model.AdoptionOrder;

import java.sql.SQLException;

public class AdoptionOrdersService {
    private AdoptionOrdersDAO adoptionOrdersDAO = new AdoptionOrdersDAO();

    public AdoptionOrder createAdoptionOrder(String userId, int catId, String adoptionDate) throws SQLException {
        return adoptionOrdersDAO.createAdoptionOrder(userId,catId,adoptionDate);
    };

}
