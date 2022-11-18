package com.revature.project2.model;

import java.util.Objects;

public class Account {
    private String uid;
    private String fname;
    private String lname;
    private String streetAddress;
    private String city;
    private String state;

    public Account() {
    }

    public Account(String uid, String fname, String lname, String streetAddress, String city, String state) {
        this.uid = uid;
        this.fname = fname;
        this.lname = lname;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return uid.equals(account.uid) && fname.equals(account.fname) && lname.equals(account.lname) && streetAddress.equals(account.streetAddress) && city.equals(account.city) && state.equals(account.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, fname, lname, streetAddress, city, state);
    }

    @Override
    public String toString() {
        return "Account{" +
                "uid='" + uid + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", streetAddress='" + streetAddress + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
