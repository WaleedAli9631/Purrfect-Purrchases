package com.revature.project2.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class AdoptionInformation {
    private String user_id;
    private int cat_id;
    private String adoption_date;

    public AdoptionInformation() {
    }

    public AdoptionInformation(String user_id, int cat_id, String adoption_date) {
        this.user_id = user_id;
        this.cat_id = cat_id;
        this.adoption_date = adoption_date;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public String getAdoption_date() {
        return adoption_date;
    }

    public void setAdoption_date(String adoption_date) {
        this.adoption_date = adoption_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdoptionInformation that = (AdoptionInformation) o;
        return cat_id == that.cat_id && Objects.equals(user_id, that.user_id) && Objects.equals(adoption_date, that.adoption_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, cat_id, adoption_date);
    }

    @Override
    public String toString() {
        return "AdoptionInformation{" +
                "user_id='" + user_id + '\'' +
                ", cat_id=" + cat_id +
                ", adoption_date='" + adoption_date + '\'' +
                '}';
    }
}
