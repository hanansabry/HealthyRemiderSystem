package com.app.healthyremidersystem.domain.usecases;

import com.app.healthyremidersystem.data.AppointmentRepository;
import com.app.healthyremidersystem.model.Appointment;

import androidx.lifecycle.MutableLiveData;

public class AddAppointmentUseCase {

    private AppointmentRepository appointmentRepository;

    public AddAppointmentUseCase(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public void execute(String userId, Appointment appointment, MutableLiveData<Boolean> success) {
        appointmentRepository.addNewAppointment(userId, appointment, success);
    }
}
