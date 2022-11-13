package com.revature.project2.dto;

public class CatInformation {
    private int catID;

    public CatInformation() {
    }

    public CatInformation(int catID) {
        this.catID = catID;
    }

    public int getCatID() {
        return catID;
    }

    public void setCatID(int catID) {
        this.catID = catID;
    }

    @Override
    public String toString() {
        return "CatInformation{" +
                "catID=" + catID +
                '}';
    }
}
