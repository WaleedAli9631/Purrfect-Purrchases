package com.revature.project2.model;

import java.util.Objects;

public class Account {
    private String uid;
    private String fname;
    private String lname;
    private String streetAddress;
    private String city;
    private String state;

    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Account() {
    }

    public Account(String uid, String fname, String lname, String streetAddress, String city, String state, String role) {
        this.uid = uid;
        this.fname = fname;
        this.lname = lname;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.role = role;
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
        return Objects.equals(uid, account.uid) && Objects.equals(fname, account.fname) && Objects.equals(lname, account.lname) && Objects.equals(streetAddress, account.streetAddress) && Objects.equals(city, account.city) && Objects.equals(state, account.state) && Objects.equals(role, account.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, fname, lname, streetAddress, city, state, role);
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
                ", role='" + role + '\'' +
                '}';
    }
}
