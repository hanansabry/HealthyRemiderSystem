package com.app.healthyremidersystem.presentation.viewmodels;

import com.app.healthyremidersystem.Injection;
import com.app.healthyremidersystem.domain.usecases.AddAppointmentUseCase;
import com.app.healthyremidersystem.model.Appointment;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddAppointmentViewModel extends ViewModel {

    private AddAppointmentUseCase addAppointmentUseCase;
    private MutableLiveData<Boolean> success = new MutableLiveData<>();
    private MutableLiveData<String> nameError = new MutableLiveData<>();
    private MutableLiveData<String> dateError = new MutableLiveData<>();
    private MutableLiveData<String> timeError = new MutableLiveData<>();

    public AddAppointmentViewModel() {
        this.addAppointmentUseCase = Injection.getAddAppointmentUseCase();
    }

    public MutableLiveData<Boolean> getSuccess() {
        return success;
    }

    public MutableLiveData<String> getNameError() {
        return nameError;
    }

    public MutableLiveData<String> getDateError() {
        return dateError;
    }

    public MutableLiveData<String> getTimeError() {
        return timeError;
    }

    public void addNewAppointment(String userId, String name, String date, String time, String notes) {
        if (validate(name, date, time)) {
            Appointment appointment = new Appointment(name, date, time, notes);
            addAppointmentUseCase.execute(userId, appointment, success);
        }
    }

    private boolean validate(String name, String date, String time) {
        boolean isValid = true;
        if (name == null || name.isEmpty()) {
            nameError.setValue("Required");
            isValid = false;
        }
        if (date == null || date.isEmpty()) {
            dateError.setValue("Required");
            isValid = false;
        }
        if (time == null || time.isEmpty()) {
            timeError.setValue("Required");
            isValid = false;
        }
        return isValid;
    }
}
