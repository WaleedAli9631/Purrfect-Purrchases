package com.revature.project2.service;

import com.revature.project2.dao.AccountDAO;
import com.revature.project2.model.Account;

import java.sql.SQLException;

public class AccountService {
    private AccountDAO accountDAO = new AccountDAO();

    public Account addAccount(String uid,
                              String fname,
                              String lname,
                              String streetAddress,
                              String city,
                              String state) throws SQLException {
        // could do some invalid arg checking here

        return accountDAO.createAccount(uid, fname, lname, streetAddress, city, state);
    }
    public Account updateAccount(String uid,
                              String fname,
                              String lname,
                              String streetAddress,
                              String city,
                              String state) throws SQLException {
        // could do some invalid arg checking here

        return accountDAO.updateAccount(uid, fname, lname, streetAddress, city, state);
    }
    public Account getAccountByUID(String uid) throws SQLException {
        return accountDAO.findAccountByUID(uid);
    }
}
