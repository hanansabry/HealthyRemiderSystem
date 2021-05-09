package com.app.healthyremidersystem.presentation.viewmodels;

import com.app.healthyremidersystem.Injection;
import com.app.healthyremidersystem.domain.usecases.AddIllnessHistoryUseCase;
import com.app.healthyremidersystem.model.IllnessHistory;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddIllnessHistoryViewModel extends ViewModel {

    private final AddIllnessHistoryUseCase addIllnessHistoryUseCase;
    private MutableLiveData<IllnessHistory> illnessHistoryLiveData = new MutableLiveData<>();
    private MutableLiveData<String> nameError = new MutableLiveData<>();
    private MutableLiveData<String> yearsError = new MutableLiveData<>();

    public AddIllnessHistoryViewModel() {
        addIllnessHistoryUseCase = Injection.getIllnessHistoryUseCase();
    }

    public void addNewIllnessHistory(String userId, String name, int years) {
        if (validate(name, years)) {
            IllnessHistory illnessHistory = new IllnessHistory(name, years);
            addIllnessHistoryUseCase.execute(userId, illnessHistory, illnessHistoryLiveData);
        }
    }

    private boolean validate(String name, int years) {
        boolean isValid = true;
        if (name == null || name.isEmpty()) {
            nameError.setValue("Required");
            isValid = false;
        }
        if (years == 0) {
            yearsError.setValue("Required");
            isValid = false;
        }
        return isValid;
    }

    public MutableLiveData<IllnessHistory> getIllnessHistoryLiveData() {
        return illnessHistoryLiveData;
    }

    public MutableLiveData<String> getNameError() {
        return nameError;
    }

    public MutableLiveData<String> getYearsError() {
        return yearsError;
    }
}
