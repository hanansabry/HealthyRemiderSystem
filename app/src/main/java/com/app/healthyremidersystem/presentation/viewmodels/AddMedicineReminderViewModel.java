package com.app.healthyremidersystem.presentation.viewmodels;

import com.app.healthyremidersystem.Injection;
import com.app.healthyremidersystem.domain.usecases.AddMedicineReminderUseCase;
import com.app.healthyremidersystem.model.Medicine;
import com.app.healthyremidersystem.model.ScheduledTime;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddMedicineReminderViewModel extends ViewModel {

    private AddMedicineReminderUseCase addMedicineReminderUseCase;
    private MutableLiveData<Boolean> success = new MutableLiveData<>();
    private MutableLiveData<String> medicineNameError = new MutableLiveData<>();
    private MutableLiveData<String> daysNumberError = new MutableLiveData<>();
    private MutableLiveData<String> numPerDayError = new MutableLiveData<>();
    private MutableLiveData<String> error = new MutableLiveData<>();

    public AddMedicineReminderViewModel() {
        this.addMedicineReminderUseCase = Injection.getAddMedicineReminderUseCase();
    }

    public MutableLiveData<Boolean> getSuccess() {
        return success;
    }

    public MutableLiveData<String> getError() {
        return error;
    }

    public boolean validate(String medicineName, String days, String numPerDay) {
        boolean isValid = true;
        if (medicineName == null || medicineName.isEmpty()) {
            medicineNameError.setValue("Required");
            isValid = false;
        }
        if (days == null || days.isEmpty()) {
            daysNumberError.setValue("Required");
            isValid = false;
        }
        if (numPerDay == null || numPerDay.isEmpty()) {
            numPerDayError.setValue("Required");
            isValid = false;
        }
        return isValid;
    }

    public void addNewMedicineReminder(String medicineName, String medicineImage,
                                       List<ScheduledTime.Day> days, int numPerDay) {

    }
}
