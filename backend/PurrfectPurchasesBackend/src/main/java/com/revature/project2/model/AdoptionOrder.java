package com.revature.project2.model;

import java.util.Objects;

public class AdoptionOrder {
    private int adoptionID;
    private String Userid;
    private int catID;
    private String adoptionDate;

    public AdoptionOrder() {
    }

    public AdoptionOrder(int adoptionID, String userid, int catID, String adoptionDate) {
        this.adoptionID = adoptionID;
        Userid = userid;
        this.catID = catID;
        this.adoptionDate = adoptionDate;
    }

    public int getAdoptionID() {
        return adoptionID;
    }

    public void setAdoptionID(int adoptionID) {
        this.adoptionID = adoptionID;
    }

    public String getUserid() {
        return Userid;
    }

    public void setUserid(String userid) {
        Userid = userid;
    }

    public int getCatID() {
        return catID;
    }

    public void setCatID(int catID) {
        this.catID = catID;
    }

    public String getAdoptionDate() {
        return adoptionDate;
    }

    public void setAdoptionDate(String adoptionDate) {
        this.adoptionDate = adoptionDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdoptionOrder that = (AdoptionOrder) o;
        return adoptionID == that.adoptionID && catID == that.catID && Objects.equals(Userid, that.Userid) && Objects.equals(adoptionDate, that.adoptionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adoptionID, Userid, catID, adoptionDate);
    }

    @Override
    public String toString() {
        return "AdoptionOrders{" +
                "adoptionID=" + adoptionID +
                ", Userid='" + Userid + '\'' +
                ", catID=" + catID +
                ", adoptionDate='" + adoptionDate + '\'' +
                '}';
    }
}
