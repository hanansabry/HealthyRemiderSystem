package com.app.healthyremidersystem.domain;

import com.app.healthyremidersystem.data.AppointmentRepository;
import com.app.healthyremidersystem.model.Appointment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.lifecycle.MutableLiveData;

public class AppointmentRepositoryImpl implements AppointmentRepository {

    private final DatabaseReference databaseReference;

    public AppointmentRepositoryImpl() {
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
    }

    @Override
    public void addNewAppointment(String userId, Appointment appointment, MutableLiveData<Appointment> returnedAppointment) {
        databaseReference.child(userId)
                .child("appointments")
                .child(String.valueOf(appointment.getId()))
                .setValue(appointment).addOnCompleteListener(task -> returnedAppointment.setValue(appointment));
    }
}
