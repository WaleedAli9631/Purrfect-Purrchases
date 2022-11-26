package com.revature.project2.dao;

import com.revature.project2.model.Account;
import org.eclipse.jetty.server.Authentication;

import java.sql.*;

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
                account.setRole(resultSet.getString("role"));
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
                                 String state,
                                 String role) throws SQLException {
        try(Connection connection = ConnectionUtility.getConnection()){
            String sql = "insert into accounts (uid, f_name, l_name, street_address, city, state) values (?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,uid);
            preparedStatement.setString(2,fname);
            preparedStatement.setString(3,lname);
            preparedStatement.setString(4,streetAddress);
            preparedStatement.setString(5,city);
            preparedStatement.setString(6,state);
            preparedStatement.setString(7,role);

            preparedStatement.execute();

            return new Account(uid,fname,lname,streetAddress,city,state,role);
        }

    }

    public Account updateAccount(String uid,
                                 String fname,
                                 String lname,
                                 String streetAddress,
                                 String city,
                                 String state) throws SQLException {
        try(Connection connection = ConnectionUtility.getConnection()){

            String sql = "update accounts set f_name=?, l_name=?, street_address=?, city=?, state=? where uid=?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1,fname);
            preparedStatement.setString(2,lname);
            preparedStatement.setString(3,streetAddress);
            preparedStatement.setString(4,city);
            preparedStatement.setString(5,state);
            preparedStatement.setString(6,uid);

            preparedStatement.execute();

            return new Account(uid,fname,lname,streetAddress,city,state);
        }

    }
}
