package com.app.healthyremidersystem.domain.usecases;

import com.app.healthyremidersystem.data.MedicineRepository;

import androidx.lifecycle.MutableLiveData;

public class RemoveMedicineUseCase {

    private MedicineRepository medicineRepository;

    public RemoveMedicineUseCase(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    public void execute(String userId, String medicineId, MutableLiveData<Boolean> success) {
        medicineRepository.removeMedicine(userId, medicineId, success);
    }
}
