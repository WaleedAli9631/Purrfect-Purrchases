package com.revature.unit;

import com.revature.project2.dao.AccountDAO;
import com.revature.project2.model.Account;
import com.revature.project2.service.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testng.Assert;

import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // Provides Jupiter with additional functionalities coming with Mockito
public class AccountUnitTests {

    @Mock
    AccountDAO mockAD;

    @InjectMocks
    AccountService accountService;

    @Test
    public void getAccountPositive() throws SQLException {
        when(mockAD.findAccountByUID("PJuTs9hFfvfMNVlWmR17t3LYEVN2")).thenReturn(new Account("PJuTs9hFfvfMNVlWmR17t3LYEVN2", "fname1", "lname1", "fake street1", "fake city1", "fake state1", null));
        Account actual = accountService.getAccountByUID("PJuTs9hFfvfMNVlWmR17t3LYEVN2");
        Account expected = new Account("PJuTs9hFfvfMNVlWmR17t3LYEVN2", "fname1", "lname1", "fake street1", "fake city1", "fake state1", null);

        Assert.assertEquals(actual,expected);
    }

    @Test
    public void getAccountNegative() throws SQLException {
        when(mockAD.findAccountByUID(any())).thenReturn(null);
        Assert.assertEquals(accountService.getAccountByUID("somerandomthing"),null);
    }

    @Test
    public void createAccount() throws SQLException {
        when(mockAD.createAccount("PJuTs9hFfvfMNVlWmR17t3LYEVN2", "fname1", "lname1", "fake street1", "fake city1", "fake state1", null)).thenReturn(new Account("PJuTs9hFfvfMNVlWmR17t3LYEVN2", "fname1", "lname1", "fake street1", "fake city1", "fake state1", null));
        Account actual = accountService.addAccount("PJuTs9hFfvfMNVlWmR17t3LYEVN2", "fname1", "lname1", "fake street1", "fake city1", "fake state1", null);
        Account expected = new Account("PJuTs9hFfvfMNVlWmR17t3LYEVN2", "fname1", "lname1", "fake street1", "fake city1", "fake state1", null);

        Assert.assertEquals(actual,expected);
    }

    @Test
    public void updateAccount() throws SQLException {
        when(mockAD.updateAccount("PJuTs9hFfvfMNVlWmR17t3LYEVN2", "fname1", "lname1", "fake street1", "fake city1", "fake state1", null)).thenReturn(new Account("PJuTs9hFfvfMNVlWmR17t3LYEVN2", "fname1", "lname1", "fake street1", "fake city1", "fake state1", null));
        Account actual = accountService.updateAccount("PJuTs9hFfvfMNVlWmR17t3LYEVN2", "fname1", "lname1", "fake street1", "fake city1", "fake state1", null);
        Account expected = new Account("PJuTs9hFfvfMNVlWmR17t3LYEVN2", "fname1", "lname1", "fake street1", "fake city1", "fake state1", null);

        Assert.assertEquals(actual,expected);
    }
}
