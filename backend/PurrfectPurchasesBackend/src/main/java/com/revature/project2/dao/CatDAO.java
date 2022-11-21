package com.revature.project2.dao;

import com.revature.project2.dto.AllCatInformation;
import com.revature.project2.dto.CatInformation;
import com.revature.project2.model.Cat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CatDAO {

    public void deleteCatById(int id) {
        try (Connection connection = ConnectionUtility.getConnection()){
            String sql = "delete from cats where cat_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1,id);
            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Cat findCatByID(int id) {
        try (Connection connection = ConnectionUtility.getConnection()){
            String sql = "select * from cats where cat_id = ?";
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
    public List<Cat> findAllCats(String breed, int age, String gender) {
        try (Connection connection = ConnectionUtility.getConnection()){
            if (breed.equals("ALL")) breed = "%";
            if (gender.equals("ALL")) gender = "%";
            String sql = "select * from cats where cat_breed like ? and cat_gender like ? order by cat_id";
            if (age != 0) {
                sql = "select * from cats where cat_breed like ? and cat_gender like ? and cat_age=? order by cat_id";
            }
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,breed);
            pstmt.setString(2,gender);
            if (age != 0) { pstmt.setInt(3,age); }
            //
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

    public Cat changeCat(CatInformation catinfo) {
        try (Connection connection = ConnectionUtility.getConnection()){
            String sql = "update cats set cat_name=?,cat_age=?,cat_breed=?,cat_color=?,cat_imageFile=?,cat_gender=?,cat_costs=? WHERE cat_id=?";
            PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,catinfo.getCatName());
            ps.setInt(2,catinfo.getCatAge());
            ps.setString(3,catinfo.getCatBreed());
            ps.setString(4,catinfo.getCatColor());
            ps.setString(5,catinfo.getCatImgName());
            ps.setString(6,catinfo.getCatGender());
            ps.setInt(7,catinfo.getCatCosts());
            ps.setInt(8,catinfo.getCatID());
            ps.execute();

            return new Cat(catinfo.getCatID(), catinfo.getCatName(),catinfo.getCatBreed(),catinfo.getCatGender(),catinfo.getCatColor(),catinfo.getCatAge(),catinfo.getCatImgName(),catinfo.getCatCosts());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Cat insertCat(CatInformation catinfo) {
        try (Connection connection = ConnectionUtility.getConnection()){
            String sql = "insert into cats (cat_name, cat_age, cat_breed, cat_color, cat_imageFile, cat_gender, cat_costs) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, catinfo.getCatName());
            ps.setInt(2, catinfo.getCatAge());
            ps.setString(3,catinfo.getCatBreed());
            ps.setString(4,catinfo.getCatColor());
            ps.setString(5,catinfo.getCatImgName());
            ps.setString(6,catinfo.getCatGender());
            ps.setInt(7,catinfo.getCatCosts());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            catinfo.setCatID(id);

            return new Cat(id, catinfo.getCatName(),catinfo.getCatBreed(),catinfo.getCatGender(),catinfo.getCatColor(),catinfo.getCatAge(),catinfo.getCatImgName(),catinfo.getCatCosts());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
