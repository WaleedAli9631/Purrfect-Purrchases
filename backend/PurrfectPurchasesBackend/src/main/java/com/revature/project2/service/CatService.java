package com.revature.project2.service;

import com.revature.project2.dao.CatDAO;
import com.revature.project2.dto.AllCatInformation;
import com.revature.project2.dto.CatInformation;
import com.revature.project2.model.Cat;

import java.sql.SQLException;
import java.util.List;

public class CatService {
    private CatDAO catDAO = new CatDAO();
    public Cat getCat(int id) throws SQLException {
        return catDAO.findCatByID(id);
    }

    public void deleteCat(int id) throws SQLException {
        catDAO.deleteCatById(id);
    }

    public Cat addCat(CatInformation catinfo) {
        return catDAO.insertCat(catinfo);
    }

    public Cat editCat(CatInformation catinfo) {
        return catDAO.changeCat(catinfo);
    }
    public Cat catPurchasedBy(CatInformation catinfo) {return catDAO.changeCatPurchasedBy(catinfo);}
    public List<Cat> getCatList(String breed, int age, String gender) {
        return catDAO.findAllCats(breed, age, gender);
    }
    public List<Cat> getUserAdoptedCats(String uid) {
        return catDAO.findUserAdoptedCats(uid);
    }

}
