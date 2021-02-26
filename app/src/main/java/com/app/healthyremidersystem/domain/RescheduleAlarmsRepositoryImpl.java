package com.app.healthyremidersystem.domain;

import com.app.healthyremidersystem.model.Medicine;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class RescheduleAlarmsRepositoryImpl {

    public interface MedicinesRetrievedCallback {
        void onRetrieveMedicines(List<Medicine> medicines);
    }

    private final DatabaseReference databaseReference;

    public RescheduleAlarmsRepositoryImpl() {
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
    }

    public void retrieveAllMedicines(String userId, MedicinesRetrievedCallback callback) {
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
                        callback.onRetrieveMedicines(medicineList);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        callback.onRetrieveMedicines(null);
                    }
                });
    }
}
