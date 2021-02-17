package com.app.healthyremidersystem.domain;

import com.app.healthyremidersystem.data.MedicineRepository;
import com.app.healthyremidersystem.model.Medicine;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

public class MedicineRepositoryImpl implements MedicineRepository {
    @Override
    public void addMedicineReminder(Medicine medicine, MutableLiveData<Boolean> success) {

    }

    @Override
    public void retrieveAllMedicinesReminders(MutableLiveData<List<Medicine>> medicines) {

    }
}
