package com.app.healthyremidersystem.domain.usecases;

import com.app.healthyremidersystem.data.MedicalHistoryRepository;
import com.app.healthyremidersystem.model.IllnessHistory;

import androidx.lifecycle.MutableLiveData;

public class AddIllnessHistoryUseCase {

    private final MedicalHistoryRepository medicalHistoryRepository;

    public AddIllnessHistoryUseCase(MedicalHistoryRepository medicalHistoryRepository) {
        this.medicalHistoryRepository = medicalHistoryRepository;
    }

    public void execute(String userId, IllnessHistory illnessHistory, MutableLiveData<IllnessHistory> illnessHistoryMLiveData) {
        medicalHistoryRepository.addNewIllnessHistory(userId, illnessHistory, illnessHistoryMLiveData);
    }
}
