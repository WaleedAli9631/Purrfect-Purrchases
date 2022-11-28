package com.revature.project2.controller;

import com.revature.project2.model.Cat;
import com.revature.project2.service.CatService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class CatValidationTest {

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
        assertEquals((returnCat != null),works);
    }

    @ParameterizedTest
    @CsvSource({
            "1,true",
            "0,false",
            "-1,false"
    })
    public void catIDpush(String id,boolean works) {
        CatValidation cat = new CatValidation();
        assertEquals(works, cat.catIDMoreThanZero(id));
    }

    @ParameterizedTest
    @CsvSource({
            "1,true",
            "Moose,false",
            "One,false"
    })
    public void catIntOrNot(String string,boolean works) {
        CatValidation cat = new CatValidation();
        assertEquals(works, cat.catIDNotInt(string));
    }

    @ParameterizedTest
    @CsvSource({
            "1,true",
            "5,true",
            "Moose,false"
    })
    public void catAgePush(String age,boolean works) {
        CatValidation cat = new CatValidation();
        assertEquals(works, cat.catAgeInBoundaries(age));
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
        assertEquals(works, cat.catAgePositive(age));
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
        assertEquals(works, cat.catBreedExists(breed));
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
        assertEquals(works, cat.catGenderExists(gender));
    }
}