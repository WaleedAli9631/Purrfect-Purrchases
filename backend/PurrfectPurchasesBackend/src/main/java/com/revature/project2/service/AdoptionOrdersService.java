package com.revature.project2.service;

import com.revature.project2.dao.AdoptionOrdersDAO;
import com.revature.project2.model.AdoptionOrder;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AdoptionOrdersService {
    private AdoptionOrdersDAO adoptionOrdersDAO = new AdoptionOrdersDAO();

    public AdoptionOrder createAdoptionOrder(String userId, int catId, String adoptionDate) throws SQLException, WrongDateException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String now = dtf.format(LocalDateTime.now());
        if (!adoptionDate.equals(now)) {
            throw new WrongDateException("This is not today's date");
        }
        return adoptionOrdersDAO.createAdoptionOrder(userId,catId,adoptionDate);
    };

}
