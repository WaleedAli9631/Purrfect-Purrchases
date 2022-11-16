package com.revature.project2.dto;

public class AllCatInformation {
    private String breed;
    private int age;
    private String gender;

    public AllCatInformation() {
    }

    public AllCatInformation(String breed, int age, String gender) {
        this.breed = breed;
        this.age = age;
        this.gender = gender;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "AllCatInformation{" +
                "breed='" + breed + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}
