package com.revature.project2.dao;

import com.revature.project2.model.AdoptionOrder;

import java.sql.*;

public class AdoptionOrdersDAO {

    public AdoptionOrder createAdoptionOrder(String userId, int catId, String adoptionDate) throws SQLException {
        try (Connection connection = ConnectionUtility.getConnection()) {
            String sql = "insert into adoption_orders(user_id,cat_id,adoption_date)\n"+
                    "values \n" +
                    "(?,?,?)";
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1,userId);
            pstmt.setInt(2,catId);
            pstmt.setString(3,adoptionDate);
            pstmt.execute();
            ResultSet rs = pstmt.getGeneratedKeys();
            rs.next();
            int adoptionID = rs.getInt("adoption_id");

            return new AdoptionOrder(adoptionID,userId,catId,adoptionDate);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
