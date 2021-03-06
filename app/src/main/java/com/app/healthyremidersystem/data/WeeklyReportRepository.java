package com.app.healthyremidersystem.data;

import com.app.healthyremidersystem.model.Medicine;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

public interface WeeklyReportRepository {

    void getTakenNumberForMedicine(String userId, String medicineId, MutableLiveData<Integer> takenNumber);
}
