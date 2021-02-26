package com.app.healthyremidersystem.model;

import android.content.Context;

import com.app.healthyremidersystem.Helper;
import com.app.healthyremidersystem.presentation.notification.Alarm;

import java.util.List;
import java.util.Random;

public class Medicine {

    private int medicineId = new Random().nextInt(1000000) + 100000000;
    private String medicineName;
    private String medicineImageUri;
    private int daysNumber;
    private int numberPerDay;
    private List<String> timesPerDay;
    private double takingMedicinePercentage;
    private String takingMedicineStatus;
    private List<ScheduledTime> scheduledTimes;

    public int getMedicineId() {
        return medicineId;
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

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public List<String> getTimesPerDay() {
        return timesPerDay;
    }

    public void setTimesPerDay(List<String> timesPerDay) {
        this.timesPerDay = timesPerDay;
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

    public List<ScheduledTime> getScheduledTimes() {
        return scheduledTimes;
    }

    public void setScheduledTimes(List<ScheduledTime> scheduledTimes) {
        this.scheduledTimes = scheduledTimes;
    }

    public int getTakenNumber() {
        int takenNumber = 0;
        for (ScheduledTime time : scheduledTimes) {
            if (time.getStatus()) {
                takenNumber++;
            }
        }
        return takenNumber;
    }

    public MedicineStatus getMedicineStatus() {
        int percent = (getTakenNumber() / scheduledTimes.size()) * 100;
        if (percent <= 100 && percent >= 90) {
            return MedicineStatus.PERFECT;
        } else if (percent < 90 && percent >= 75) {
            return MedicineStatus.ACCEPTED;
        } else {
            return MedicineStatus.CARELESS;
        }
    }

    public void setMedicineAlarms(Context context) {
        for (int i = 0; i < getScheduledTimes().size(); i++) {
            ScheduledTime scheduledTime = getScheduledTimes().get(i);
            int[] hourMinutes = new Helper(context).splitTimeIntoHourAndMinute(scheduledTime.getTime());

            Alarm alarm = new Alarm(i, 0, 0, ScheduledTime.Day.valueOf(scheduledTime.getDay()).getDayValue(),
                    hourMinutes[0], hourMinutes[1],
                    getMedicineName(),
                    getMedicineId(),
                    getMedicineId() + i,
                    true);
            alarm.schedule(context);
        }
    }

    public void removeMedicineAlarms(Context context) {
        for (int i = 0; i < getScheduledTimes().size(); i++) {
            ScheduledTime scheduledTime = getScheduledTimes().get(i);
            Alarm alarm = new Alarm();
            alarm.cancelAlarm(context, getMedicineId() + i);
        }
    }

    public enum MedicineStatus {
        PERFECT, ACCEPTED, CARELESS
    }
}
