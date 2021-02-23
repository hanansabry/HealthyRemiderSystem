package com.app.healthyremidersystem.domain.usecases;

import com.app.healthyremidersystem.data.MedicineRepository;
import com.app.healthyremidersystem.model.Medicine;

import androidx.lifecycle.MutableLiveData;

public class AddMedicineReminderUseCase {

    private MedicineRepository medicineRepository;

    public AddMedicineReminderUseCase(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    public void execute(String userId, Medicine medicine, MutableLiveData<Boolean> success) {
        medicineRepository.addMedicineReminder(userId, medicine, success);
    }
}
