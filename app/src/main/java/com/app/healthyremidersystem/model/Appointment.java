package com.app.healthyremidersystem.model;

import java.util.Random;

public class Appointment {

    private int id = new Random().nextInt(1000000) + 100000000;
    private String placeName;
    private String day;
    private String time;
    private String notes;

    public Appointment(String placeName, String day, String time, String notes) {
        this.placeName = placeName;
        this.day = day;
        this.time = time;
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
