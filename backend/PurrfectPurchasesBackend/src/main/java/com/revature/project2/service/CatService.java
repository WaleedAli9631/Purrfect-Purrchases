package com.revature.project2.service;

import com.revature.project2.dao.CatDAO;
import com.revature.project2.dto.CatInformation;
import com.revature.project2.controller.CatValidation;
import com.revature.project2.model.Cat;

import java.sql.SQLException;
import java.util.List;

public class CatService {
    private CatDAO catDAO = new CatDAO();
    private CatValidation catValidation = new CatValidation();
    public Cat getCat(int id) throws SQLException {
        return catDAO.findCatByID(id);
    }

    public boolean deleteCat(int id) throws SQLException {
        return catDAO.deleteCatById(id);
    }

    public void purchaseCat(int id, String purchaseBy) throws SQLException {
        catDAO.purCat(id, purchaseBy);
    }
    public Cat addCat(CatInformation catinfo) {
        return catDAO.insertCat(catinfo);
    }

    public Cat editCat(CatInformation catinfo) {
        return catDAO.changeCat(catinfo);
    }

    public List<Cat> getCatList(String breed, int age, String gender) {
        return catDAO.findAllCats(breed, age, gender);
    }
    public List<Cat> getUserAdoptedCats(String uid) {
        return catDAO.findUserAdoptedCats(uid);
    }

}
