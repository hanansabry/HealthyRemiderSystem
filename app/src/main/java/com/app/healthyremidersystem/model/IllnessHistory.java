package com.app.healthyremidersystem.model;

import java.util.Random;

public class IllnessHistory {

    private int id = new Random().nextInt(1000000) + 100000000;;
    private String illnessName;
    private int illnessYears;

    public IllnessHistory() {
    }

    public IllnessHistory(String illnessName, int illnessYears) {
        this.illnessName = illnessName;
        this.illnessYears = illnessYears;
    }

    public int getId() {
        return id;
    }

    public String getIllnessName() {
        return illnessName;
    }

    public void setIllnessName(String illnessName) {
        this.illnessName = illnessName;
    }

    public int getIllnessYears() {
        return illnessYears;
    }

    public void setIllnessYears(int illnessYears) {
        this.illnessYears = illnessYears;
    }
}
