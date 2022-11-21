package com.revature.project2.model;

public class Cat {
    private int id;
    private String name;
    private String breed;
    private String gender;
    private String color;
    private int age;
    private String imageFile;
    private int costs;
    private String purchasedBy;

    public int getCosts() {
        return costs;
    }

    public void setCosts(int costs) {
        this.costs = costs;
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    public Cat() {
    }

    public Cat(int id, String name, String breed, String gender, String color, int age, String imageFile, int costs, String purchasedBy) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.gender = gender;
        this.color = color;
        this.age = age;
        this.imageFile = imageFile;
        this.costs = costs;
        this.purchasedBy = purchasedBy;
    }

    public Cat(int id, String name, String breed, String gender, String color, int age, String imageFile, int costs) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.gender = gender;
        this.color = color;
        this.age = age;
        this.imageFile = imageFile;
        this.costs = costs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPurchasedBy() {
        return purchasedBy;
    }

    public void setPurchasedBy(String purchasedBy) {
        this.purchasedBy = purchasedBy;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", breed='" + breed + '\'' +
                ", gender='" + gender + '\'' +
                ", color='" + color + '\'' +
                ", age=" + age +
                ", imageFile='" + imageFile + '\'' +
                ", costs=" + costs +
                ", purchasedBy='" + purchasedBy + '\'' +
                '}';
    }
}
