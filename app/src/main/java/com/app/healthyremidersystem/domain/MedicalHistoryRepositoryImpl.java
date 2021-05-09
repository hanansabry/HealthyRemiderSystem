package com.app.healthyremidersystem.domain;

import com.app.healthyremidersystem.data.MedicalHistoryRepository;
import com.app.healthyremidersystem.data.MedicineRepository;
import com.app.healthyremidersystem.model.Appointment;
import com.app.healthyremidersystem.model.IllnessHistory;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.lifecycle.MutableLiveData;

public class MedicalHistoryRepositoryImpl implements MedicalHistoryRepository {
    private final DatabaseReference databaseReference;

    public MedicalHistoryRepositoryImpl() {
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
    }

    @Override
    public void addNewIllnessHistory(String userId, IllnessHistory illnessHistory, MutableLiveData<IllnessHistory> illnessHistoryLiveData) {
        databaseReference.child(userId)
                .child("medicalHistory")
                .child(String.valueOf(illnessHistory.getId()))
                .setValue(illnessHistory).addOnCompleteListener(task -> illnessHistoryLiveData.setValue(illnessHistory));
    }
}
