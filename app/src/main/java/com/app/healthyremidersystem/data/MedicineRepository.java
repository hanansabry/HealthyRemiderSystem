package com.app.healthyremidersystem.data;

import com.app.healthyremidersystem.model.Medicine;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

public interface MedicineRepository {

    void addMedicineReminder(Medicine medicine, MutableLiveData<Boolean> success);
    void retrieveAllMedicinesReminders(MutableLiveData<List<Medicine>> medicines);
}
