package com.revature.project2.controller;
import java.util.Arrays;
import java.util.List;

public class CatValidation {

    public boolean catIDMoreThanZero (String id) {
        if (Integer.parseInt(id) > 0) return true;
        else return false;
    }

    public boolean catIDNotInt (String id) {
        try {
            Integer.parseInt(id);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }

    public boolean catAgeInBoundaries (String age) {
        try {
            Integer.parseInt(age);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }

    public boolean catAgePositive (String age) {
        if (Integer.parseInt(age) >= 0 && Integer.parseInt(age) < 21) return true;
        else return false;
    }

    public boolean catBreedExists (String breed) {
        String[] breedArray = {"ALL","Breed","Abyssian","Aegean","American Bobtail","American Curl","Asian","Balinese","Bambino","Bengal","Birman","Bombay","Burmese","Chartreux","Himalayan","Mekong Bobtail","Persian","Ragdoll","Savannah","Siamese","Snowshoe","Somali","Sphynx","Tabby"};
        List<String> breedList = Arrays.asList(breedArray);
        boolean doesContain = breedList.contains(breed);
        return doesContain;
    }

    public boolean catGenderExists (String gender) {
        String[] values = {"ALL","Gender","Male","Female"};
        List<String> genderList = Arrays.asList(values);
        boolean doesContain = genderList.contains(gender);
        return doesContain;
    }
}
