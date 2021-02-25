package com.app.healthyremidersystem.domain;

import com.app.healthyremidersystem.data.MedicineRepository;
import com.app.healthyremidersystem.model.Medicine;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

public class MedicineRepositoryImpl implements MedicineRepository {

    private final DatabaseReference databaseReference;

    public MedicineRepositoryImpl() {
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
    }

    @Override
    public void addMedicineReminder(String userId, Medicine medicine, MutableLiveData<Medicine> retrievedMedicine) {
        databaseReference.child(userId)
                .child("medicines")
                .child(String.valueOf(medicine.getMedicineId())).setValue(medicine)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        retrievedMedicine.setValue(medicine);
                    } else {
                        retrievedMedicine.setValue(null);
                    }
                });
    }

    @Override
    public void retrieveAllMedicinesReminders(String userId, MutableLiveData<List<Medicine>> medicines) {
        databaseReference.child(userId)
                .child("medicines")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        List<Medicine> medicineList = new ArrayList<>();
                        for (DataSnapshot medicineSnapshot : snapshot.getChildren()) {
                            Medicine medicine = medicineSnapshot.getValue(Medicine.class);
                            medicineList.add(medicine);
                        }
                        medicines.setValue(medicineList);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    @Override
    public void removeMedicine(String userId, String medicineId, MutableLiveData<Boolean> success) {
        databaseReference.child(userId).child("medicines").child(medicineId).removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                success.setValue(error==null);
            }
        });
    }

    @Override
    public void updateScheduledTimeStatus(String userId, String medicineId, int timePosition, boolean done) {
        HashMap<String, Object> updatedStatus = new HashMap<>();
        updatedStatus.put("status", done);
        databaseReference.child(userId)
                .child("medicines")
                .child(medicineId)
                .child("times")
                .child(String.valueOf(timePosition))
                .updateChildren(updatedStatus);
    }
}
