package com.revature.unit;

import com.revature.project2.dao.AccountDAO;
import com.revature.project2.dao.CatDAO;
import com.revature.project2.model.Account;
import com.revature.project2.model.Cat;
import com.revature.project2.service.AccountService;
import com.revature.project2.service.CatService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testng.Assert;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // Provides Jupiter with additional functionalities coming with Mockito
public class CatUnitTests {
    @Mock
    CatDAO mockCatDAD;

    @InjectMocks
    CatService catService;

    @Test
    public void getUserCatPositive() throws SQLException {
        when(mockCatDAD.findUserAdoptedCats("PJuTs9hFfvfMNVlWmR17t3LYEVN2")).thenReturn(new ArrayList<Cat>(){{add(new Cat(1, "Kitty Kitty Bang Bang", "Ragdoll", "Male", "yellow", 4, "assets/img/cat13.jpg", 60, "PJuTs9hFfvfMNVlWmR17t3LYEVN2"));}});
                
        List<Cat> actual = catService.getUserAdoptedCats("PJuTs9hFfvfMNVlWmR17t3LYEVN2");
        List<Cat> expected = new ArrayList();;
        Cat cat = (new Cat(1, "Kitty Kitty Bang Bang", "Ragdoll", "Male", "yellow", 4, "assets/img/cat13.jpg", 60, "PJuTs9hFfvfMNVlWmR17t3LYEVN2"));
        Assert.assertEquals(actual.get(0).getId(),cat.getId());
    }

    @Test
    public void getUserCatNegative() throws SQLException {
        when(mockCatDAD.findUserAdoptedCats(any())).thenReturn(new ArrayList<Cat>() {
        });
        Assert.assertEquals(catService.getUserAdoptedCats("somerandomthing"),new ArrayList<Cat>());
    }
}
