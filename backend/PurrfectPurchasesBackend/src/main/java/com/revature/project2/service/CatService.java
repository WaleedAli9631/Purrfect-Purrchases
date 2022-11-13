package com.revature.project2.service;

import com.revature.project2.dao.CatDAO;
import com.revature.project2.model.Cat;

import java.sql.SQLException;
import java.util.List;

public class CatService {
    private CatDAO catDAO = new CatDAO();
    public Cat getCat(int id) throws SQLException {
        return catDAO.findCatByID(id);
    }
    public List<Cat> getCatList() {
        return catDAO.findAllCats();
    }

}
