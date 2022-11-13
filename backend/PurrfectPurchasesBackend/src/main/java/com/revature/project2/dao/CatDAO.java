package com.revature.project2.dao;

import com.revature.project2.model.Cat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CatDAO {
    public Cat findCatByID(int id) {
        try (Connection connection = ConnectionUtility.getConnection()){
            String sql = "select * from cats where cat_id = ?"; //"";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1,id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Cat cat = new Cat();
                cat.setId(rs.getInt("cat_id"));
                cat.setName(rs.getString("cat_name"));
                cat.setAge(rs.getInt("cat_age"));
                cat.setBreed(rs.getString("cat_breed"));
                cat.setColor(rs.getString("cat_color"));
                cat.setImageFile(rs.getString("cat_imageFile"));
                cat.setGender(rs.getString("cat_gender"));
                cat.setCosts(rs.getInt("cat_costs"));

                return cat;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Cat> findAllCats() {
        try (Connection connection = ConnectionUtility.getConnection()){
            String sql = "select * from cats"; //"";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            List<Cat> catList = new ArrayList<Cat>();
            while (rs.next()) {
                Cat cat = new Cat();
                cat.setId(rs.getInt("cat_id"));
                cat.setName(rs.getString("cat_name"));
                cat.setAge(rs.getInt("cat_age"));
                cat.setBreed(rs.getString("cat_breed"));
                cat.setColor(rs.getString("cat_color"));
                cat.setImageFile(rs.getString("cat_imageFile"));
                cat.setGender(rs.getString("cat_gender"));
                cat.setCosts(rs.getInt("cat_costs"));
                catList.add(cat);
            }
            return catList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
