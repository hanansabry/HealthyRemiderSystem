package com.app.healthyremidersystem.domain.usecases;

import com.app.healthyremidersystem.data.MedicineRepository;
import com.app.healthyremidersystem.model.Medicine;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

public class RetrieveMedicinesRemindersUseCase {

    private MedicineRepository medicineRepository;

    public RetrieveMedicinesRemindersUseCase(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    public void execute(MutableLiveData<List<Medicine>> medicines) {
        medicineRepository.retrieveAllMedicinesReminders(medicines);
    }
}
