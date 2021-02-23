package com.app.healthyremidersystem.data;

import com.app.healthyremidersystem.model.Medicine;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

public interface MedicineRepository {

    void addMedicineReminder(String userId, Medicine medicine, MutableLiveData<Boolean> success);
    void retrieveAllMedicinesReminders(String userId, MutableLiveData<List<Medicine>> medicines);
    void removeMedicine(String userId, String medicineId, MutableLiveData<Boolean> success);
}
