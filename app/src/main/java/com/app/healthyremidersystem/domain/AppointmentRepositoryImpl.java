package com.app.healthyremidersystem.domain;

import com.app.healthyremidersystem.data.AppointmentRepository;
import com.app.healthyremidersystem.model.Appointment;

import androidx.lifecycle.MutableLiveData;

public class AppointmentRepositoryImpl implements AppointmentRepository {
    @Override
    public void addNewAppointment(Appointment appointment, MutableLiveData<Boolean> success) {

    }
}
