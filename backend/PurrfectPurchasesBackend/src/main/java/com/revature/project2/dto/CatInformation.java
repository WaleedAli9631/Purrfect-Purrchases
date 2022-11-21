package com.revature.project2.dto;

public class CatInformation {
    private int catID;
    private String catName;
    private String catBreed;
    private String catGender;
    private int catAge;
    private int catCosts;
    private String catImgName;
    private String catColor;
    private String purchasedBy;

    public CatInformation() {
    }

    public CatInformation(int catID, String catName, String catBreed, String catGender, int catAge, int catCosts, String catImgName, String catColor, String purchasedBy) {
        this.catID = catID;
        this.catName = catName;
        this.catBreed = catBreed;
        this.catGender = catGender;
        this.catAge = catAge;
        this.catCosts = catCosts;
        this.catImgName = catImgName;
        this.catColor = catColor;
        this.purchasedBy = purchasedBy;
    }

    public CatInformation(int catID, String catName, String catBreed, String catGender, int catAge, int catCosts, String catImgName, String catColor) {
        this.catID = catID;
        this.catName = catName;
        this.catBreed = catBreed;
        this.catGender = catGender;
        this.catAge = catAge;
        this.catCosts = catCosts;
        this.catImgName = catImgName;
        this.catColor = catColor;
    }

    public int getCatID() {
        return catID;
    }

    public void setCatID(int catID) {
        this.catID = catID;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCatBreed() {
        return catBreed;
    }

    public void setCatBreed(String catBreed) {
        this.catBreed = catBreed;
    }

    public String getCatGender() {
        return catGender;
    }

    public void setCatGender(String catGender) {
        this.catGender = catGender;
    }

    public int getCatAge() {
        return catAge;
    }

    public void setCatAge(int catAge) {
        this.catAge = catAge;
    }

    public int getCatCosts() {
        return catCosts;
    }

    public void setCatCosts(int catCosts) {
        this.catCosts = catCosts;
    }

    public String getCatImgName() {
        return catImgName;
    }

    public void setCatImgName(String catImgName) {
        this.catImgName = catImgName;
    }

    public String getCatColor() {
        return catColor;
    }

    public void setCatColor(String catColor) {
        this.catColor = catColor;
    }

    public String getPurchasedBy() {
        return purchasedBy;
    }

    public void setPurchasedBy(String purchasedBy) {
        this.purchasedBy = purchasedBy;
    }

    @Override
    public String toString() {
        return "CatInformation{" +
                "catID=" + catID +
                ", catName='" + catName + '\'' +
                ", catBreed='" + catBreed + '\'' +
                ", catGender='" + catGender + '\'' +
                ", catAge=" + catAge +
                ", catCosts=" + catCosts +
                ", catImgName='" + catImgName + '\'' +
                ", catColor='" + catColor + '\'' +
                ", purchasedBy='" + purchasedBy + '\'' +
                '}';
    }
}
