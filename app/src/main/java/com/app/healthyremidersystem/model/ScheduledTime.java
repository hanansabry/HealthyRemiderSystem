package com.app.healthyremidersystem.model;

public class ScheduledTime {

    private String day;
    private String time;
    private boolean status;

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

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public enum Day {
        SAT, SUN, MON, TUE, WED, THU, FRI
    }
}
