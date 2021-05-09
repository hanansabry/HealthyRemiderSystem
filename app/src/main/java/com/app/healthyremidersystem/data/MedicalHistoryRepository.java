package com.app.healthyremidersystem.data;

import com.app.healthyremidersystem.model.Appointment;
import com.app.healthyremidersystem.model.IllnessHistory;

import androidx.lifecycle.MutableLiveData;

public interface MedicalHistoryRepository {

    void addNewIllnessHistory(String userId, IllnessHistory illnessHistory, MutableLiveData<IllnessHistory> illnessHistoryLiveData);
}
