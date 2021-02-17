package com.app.healthyremidersystem.model;

public class User {

    private String id;
    private String fullName;
    private String email;
    private String phone;
    private double weight;
    private double height;
    private String gender;

    public User(String fullName,  String phone, String email, double weight, double height, String gender) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.weight = weight;
        this.height = height;
        this.gender = gender;
    }

    public User(String fullName, String phone, String email, String password, double weight, double length) {
        this.fullName = fullName;
        this. email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
