package com.revature.project2.dao;

import com.revature.project2.model.Account;
import org.eclipse.jetty.server.Authentication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO {
    public Account findAccountByUID(String uid) throws SQLException {
        try (Connection connection = ConnectionUtility.getConnection()) {
            String sql = "select * from accounts where uid = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, uid);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Account account = new Account();
                account.setUid(resultSet.getString("uid"));
                account.setFname(resultSet.getString("f_name"));
                account.setLname(resultSet.getString("l_name"));
                account.setStreetAddress(resultSet.getString("street_address"));
                account.setCity(resultSet.getString("city"));
                account.setState(resultSet.getString("state"));

                return account;
            }
            return null;
        }
    }

    public Account createAccount(String uid,
                                 String fname,
                                 String lname,
                                 String streetAddress,
                                 String city,
                                 String state) throws SQLException {
        try(Connection connection = ConnectionUtility.getConnection()){
            String sql = "insert into accounts (uid, f_name, l_name, street_address, city, state) values (?,?,?,?,?,?)";
        }
    }
}
