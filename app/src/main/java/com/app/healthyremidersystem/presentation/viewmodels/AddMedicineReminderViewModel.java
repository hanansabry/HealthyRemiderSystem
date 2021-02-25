package com.app.healthyremidersystem.presentation.viewmodels;

import com.app.healthyremidersystem.Injection;
import com.app.healthyremidersystem.domain.usecases.AddMedicineReminderUseCase;
import com.app.healthyremidersystem.domain.usecases.RemoveMedicineUseCase;
import com.app.healthyremidersystem.model.Medicine;
import com.app.healthyremidersystem.model.ScheduledTime;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddMedicineReminderViewModel extends ViewModel {

    private AddMedicineReminderUseCase addMedicineReminderUseCase;
    private RemoveMedicineUseCase removeMedicineUseCase;
    private MutableLiveData<Medicine> retrievedMedicine = new MutableLiveData<>();
    private MutableLiveData<Boolean> removeSuccess = new MutableLiveData<>();
    private MutableLiveData<String> medicineNameError = new MutableLiveData<>();
    private MutableLiveData<String> daysNumberError = new MutableLiveData<>();
    private MutableLiveData<String> numPerDayError = new MutableLiveData<>();
    private MutableLiveData<String> error = new MutableLiveData<>();

    public AddMedicineReminderViewModel() {
        this.addMedicineReminderUseCase = Injection.getAddMedicineReminderUseCase();
        this.removeMedicineUseCase = Injection.getRemoveMedicineUseCase();
    }

    public MutableLiveData<Medicine> getRetrievedMedicine() {
        return retrievedMedicine;
    }

    public MutableLiveData<Boolean> getRemoveSuccess() {
        return removeSuccess;
    }

    public MutableLiveData<String> getError() {
        return error;
    }

    public MutableLiveData<String> getMedicineNameError() {
        return medicineNameError;
    }

    public MutableLiveData<String> getDaysNumberError() {
        return daysNumberError;
    }

    public MutableLiveData<String> getNumPerDayError() {
        return numPerDayError;
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

    public void addNewMedicineReminder(String userId, String medicineName, String medicineImage, int daysNumber,
                                       List<ScheduledTime> scheduledTimes, int numPerDay) {
        Medicine medicine = new Medicine();
        medicine.setMedicineName(medicineName);
        medicine.setMedicineImageUri(medicineImage);
        medicine.setDaysNumber(daysNumber);
        medicine.setNumberPerDay(numPerDay);
        medicine.setTimes(scheduledTimes);
        addMedicineReminderUseCase.execute(userId, medicine, retrievedMedicine);
    }

    public List<ScheduledTime> getAllScheduledDaysWithTimes(List<String> scheduledTimes, List<ScheduledTime.Day> selectedDays) {
        List<ScheduledTime> times = new ArrayList<>();
        for (ScheduledTime.Day day : selectedDays) {
            for (String time : scheduledTimes) {
                ScheduledTime scheduledTime = new ScheduledTime();
                scheduledTime.setTime(time);
                scheduledTime.setDay(day.name());
                times.add(scheduledTime);
            }
        }
        return times;
    }

    public void removeMedicine(String userId, String medicineId) {
        removeMedicineUseCase.execute(userId, medicineId, removeSuccess);
    }
}
