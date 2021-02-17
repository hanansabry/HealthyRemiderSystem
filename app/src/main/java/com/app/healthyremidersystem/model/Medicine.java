package com.app.healthyremidersystem.model;

import java.util.List;

public class Medicine {

    private String id;
    private String medicineName;
    private String medicineImageUri;
    private int daysNumber;
    private int numberPerDay;
    private double takingMedicinePercentage;
    private String takingMedicineStatus;
    private List<ScheduledTime> times;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getMedicineImageUri() {
        return medicineImageUri;
    }

    public void setMedicineImageUri(String medicineImageUri) {
        this.medicineImageUri = medicineImageUri;
    }

    public int getDaysNumber() {
        return daysNumber;
    }

    public void setDaysNumber(int daysNumber) {
        this.daysNumber = daysNumber;
    }

    public int getNumberPerDay() {
        return numberPerDay;
    }

    public void setNumberPerDay(int numberPerDay) {
        this.numberPerDay = numberPerDay;
    }

    public double getTakingMedicinePercentage() {
        return takingMedicinePercentage;
    }

    public void setTakingMedicinePercentage(double takingMedicinePercentage) {
        this.takingMedicinePercentage = takingMedicinePercentage;
    }

    public String getTakingMedicineStatus() {
        return takingMedicineStatus;
    }

    public void setTakingMedicineStatus(String takingMedicineStatus) {
        this.takingMedicineStatus = takingMedicineStatus;
    }

    public List<ScheduledTime> getTimes() {
        return times;
    }

    public void setTimes(List<ScheduledTime> times) {
        this.times = times;
    }
}
