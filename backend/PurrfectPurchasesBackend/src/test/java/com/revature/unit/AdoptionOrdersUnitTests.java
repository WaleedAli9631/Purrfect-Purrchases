package com.revature.unit;


import com.revature.project2.dao.AdoptionOrdersDAO;
import com.revature.project2.model.AdoptionOrder;
import com.revature.project2.service.AdoptionOrdersService;
import com.revature.project2.service.WrongDateException;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.testng.Assert;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AdoptionOrdersUnitTests {

    @Mock
    AdoptionOrdersDAO mockAdoptionOrderDAD;

    @InjectMocks
    AdoptionOrdersService adoptionOrdersService;

    @Test
    public void addAdoptionOrderPositive() throws SQLException, WrongDateException {
        when(mockAdoptionOrderDAD.createAdoptionOrder("1", 2, "11/28/2022")).thenReturn(new AdoptionOrder(1,"1",2,"11/28/2022"));
        AdoptionOrder actual = adoptionOrdersService.createAdoptionOrder("1",2,"11/28/2022");
        AdoptionOrder expected = new AdoptionOrder(1,"1",2,"11/28/2022");
        Assert.assertEquals(actual,expected);
    }
    @Test
    public void addAdoptionOrderWrongDate() throws SQLException, WrongDateException {
        Boolean flag = false;
        try{
            when(mockAdoptionOrderDAD.createAdoptionOrder("1", 2, "11/28/2000")).thenReturn(new AdoptionOrder(1,"1",2,"11/28/2000"));
            AdoptionOrder actual = adoptionOrdersService.createAdoptionOrder("1",2,"11/28/2000");
            AdoptionOrder expected = new AdoptionOrder(1,"1",2,"11/28/2000");
            Assert.assertEquals(actual,expected);
        }
        catch (WrongDateException wde){
            flag = true;
        }
        Assert.assertTrue(flag);
    }

}
