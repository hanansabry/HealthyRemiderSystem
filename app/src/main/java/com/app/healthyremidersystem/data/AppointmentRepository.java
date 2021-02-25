package com.app.healthyremidersystem.data;

import com.app.healthyremidersystem.model.Appointment;

import androidx.lifecycle.MutableLiveData;

public interface AppointmentRepository {

    void addNewAppointment(String userId, Appointment appointment, MutableLiveData<Appointment> returnedAppointment);
}
