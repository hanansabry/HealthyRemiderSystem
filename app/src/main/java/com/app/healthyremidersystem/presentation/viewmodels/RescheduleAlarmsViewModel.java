package com.app.healthyremidersystem.presentation.viewmodels;

import com.app.healthyremidersystem.Helper;
import com.app.healthyremidersystem.domain.RescheduleAlarmsRepositoryImpl;
import com.app.healthyremidersystem.model.Medicine;

import java.util.List;

public class RescheduleAlarmsViewModel {

    private String userId;

    public RescheduleAlarmsViewModel(String userId) {
        this.userId = userId;
    }

    public void rescheduleAlarms(RescheduleAlarmsRepositoryImpl.MedicinesRetrievedCallback callback) {
        RescheduleAlarmsRepositoryImpl rescheduleAlarmsRepository = new RescheduleAlarmsRepositoryImpl();
        rescheduleAlarmsRepository.retrieveAllMedicines(userId, callback);
    }

}
