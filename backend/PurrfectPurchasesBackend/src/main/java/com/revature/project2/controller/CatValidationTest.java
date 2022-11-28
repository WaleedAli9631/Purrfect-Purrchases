package com.revature.project2.controller;

import com.revature.project2.dto.CatInformation;
import com.revature.project2.model.Cat;
import com.revature.project2.service.CatService;
import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class CatValidationTest {

    @ParameterizedTest
    @CsvSource({
            "ALL,ALL,0,true",
            "Fart,ALL,0,false",
            "ALL,ALL,1,true"
    })
    public void catCheckAll(String breed, String gender, int age, boolean works) throws SQLException {
        CatService cat = new CatService();
        boolean isFound = true;
        if (cat.getCatList(breed,age,gender).isEmpty()) isFound = false;
        assertEquals(works, isFound);
    }

    @ParameterizedTest
    @CsvSource({
            "true,Daisy,Abyssian,Female,Red,5,assets/img/cat10.jpg,50,",
    })
    public void pushCat(boolean works, String name, String breed, String gender, String color, int age,String imageFile, int costs, String purchasedBy) throws SQLException {
        CatService catService = new CatService();
        boolean isFound = true;
        CatInformation cat = new CatInformation("1",1,name,breed,gender,age,costs,imageFile,color,purchasedBy);
        if (catService.addCat(cat) == null) {
            isFound = false;
        } else {
            //Let's delete the cat to ensure my testing doesn't get obnoxious
            catService.deleteCat(cat.getCatID());
        }
        assertEquals(works, isFound);
    }

    @ParameterizedTest
    @CsvSource({
            "true,82,Daisy,Abyssian,Female,Red,5,assets/img/cat10.jpg,50,",
    })
    public void editTheCat(boolean works, int id, String name, String breed, String gender, String color, int age,String imageFile, int costs, String purchasedBy) throws SQLException {
        CatService catService = new CatService();
        boolean isFound = true;
        CatInformation cat = new CatInformation("1",id,name,breed,gender,age,costs,imageFile,color,purchasedBy);
        if (catService.editCat(cat) == null) {
            isFound = false;
        }
        assertEquals(works, isFound);
    }


    @ParameterizedTest
    @CsvSource({
            "0,false",
            "9999,false",
            "3,true"
    })
    public void catCheckID(String id, boolean works) throws SQLException {
        CatService cat = new CatService();
        boolean isFound = true;
        if (cat.getCat(Integer.parseInt(id)) == null) isFound = false;
        assertEquals(works, isFound);
    }

    @ParameterizedTest
    @CsvSource({
            "0,false",
            "9999,false"
    })
    public void catKillID(String id, boolean works) throws SQLException {
        CatService cat = new CatService();
        boolean itWorks = true;
        assertEquals(works, cat.deleteCat(Integer.parseInt(id)));
    }

    @ParameterizedTest
    @CsvSource({
            "0,false",
            "1,false",
            "2,true",
            "3,true",
            "500,false"
    })
    public void catGetID(String id, boolean works) throws SQLException {
        CatService cat = new CatService();
        Cat returnCat = cat.getCat(Integer.parseInt(id));
        Assertions.assertEquals((returnCat != null),works);
    }

    @ParameterizedTest
    @CsvSource({
            "1,true",
            "0,false",
            "-1,false"
    })
    public void catIDpush(String id,boolean works) {
        CatValidation cat = new CatValidation();
        Assertions.assertEquals(works, cat.catIDMoreThanZero(id));
    }

    @ParameterizedTest
    @CsvSource({
            "1,true",
            "Moose,false",
            "One,false"
    })
    public void catIntOrNot(String string,boolean works) {
        CatValidation cat = new CatValidation();
        Assertions.assertEquals(works, cat.catIDNotInt(string));
    }

    @ParameterizedTest
    @CsvSource({
            "1,true",
            "5,true",
            "Moose,false"
    })
    public void catAgePush(String age,boolean works) {
        CatValidation cat = new CatValidation();
        Assertions.assertEquals(works, cat.catAgeInBoundaries(age));
    }

    @ParameterizedTest
    @CsvSource({
            "-20,false",
            "-1,false",
            "0,true",
            "1,true",
            "5,true",
            "10,true",
            "15,true",
            "20,true",
            "21,false",
            "25,false"
    })
    public void catAgeInWindow(String age,boolean works) {
        CatValidation cat = new CatValidation();
        Assertions.assertEquals(works, cat.catAgePositive(age));
    }

    @ParameterizedTest
    @CsvSource({
            "Teddy Bear,false",
            "Chicken Licken,false",
            "Abyssian,true",
            "Asian,true",
            "Balinese,true",
            "Birman,true",
            "Bengal,true",
            "Siamese,true",
            "Dalmation,false",
            "Husky,false"
    })
    public void doesBreedExist(String breed,boolean works) {
        CatValidation cat = new CatValidation();
        Assertions.assertEquals(works, cat.catBreedExists(breed));
    }

    @ParameterizedTest
    @CsvSource({
            "Plato,false",
            "Socrates,false",
            "Male,true",
            "Female,true",
            "ALL,true",
            "Gender,true",
            "Aristotle,false",
            "Augustine,false",
            "Aquinas,false",
            "Hegel,false"
    })
    public void doesGenderExist(String gender,boolean works) {
        CatValidation cat = new CatValidation();
        Assertions.assertEquals(works, cat.catGenderExists(gender));
    }
}
