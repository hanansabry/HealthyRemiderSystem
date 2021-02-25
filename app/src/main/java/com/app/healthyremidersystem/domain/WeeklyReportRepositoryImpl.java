package com.app.healthyremidersystem.domain;

import com.app.healthyremidersystem.data.WeeklyReportRepository;
import com.app.healthyremidersystem.model.Medicine;
import com.app.healthyremidersystem.model.ScheduledTime;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

public class WeeklyReportRepositoryImpl implements WeeklyReportRepository {

    private final DatabaseReference databaseReference;

    public WeeklyReportRepositoryImpl() {
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
    }

    @Override
    public void getTakenNumberForMedicine(String userId, String medicineId, MutableLiveData<Integer> takenNumber) {
        databaseReference.child(userId)
                .child("medicines")
                .child(medicineId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        int num = 0;
                        Medicine medicine = snapshot.getValue(Medicine.class);
                        for (ScheduledTime time : medicine.getTimes()) {
                            if (time.getStatus()) {
                                num++;
                            }
                        }
                        takenNumber.setValue(num);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}
