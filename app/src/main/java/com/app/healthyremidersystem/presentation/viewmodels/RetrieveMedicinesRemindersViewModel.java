package com.app.healthyremidersystem.presentation.viewmodels;

import com.app.healthyremidersystem.Injection;
import com.app.healthyremidersystem.domain.usecases.RetrieveMedicinesRemindersUseCase;
import com.app.healthyremidersystem.model.Medicine;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RetrieveMedicinesRemindersViewModel extends ViewModel {

    private RetrieveMedicinesRemindersUseCase retrieveMedicinesRemindersUseCase;
    private MutableLiveData<List<Medicine>> medicines = new MutableLiveData<>();
    private MutableLiveData<String> error = new MutableLiveData<>();

    public RetrieveMedicinesRemindersViewModel() {
        this.retrieveMedicinesRemindersUseCase = Injection.getRetrieveMedicinesRemindersUseCase();
    }

    public MutableLiveData<List<Medicine>> getMedicines() {
        return medicines;
    }

    public MutableLiveData<String> getError() {
        return error;
    }

    public void retrieveMedicinesReminders(String userId) {
        retrieveMedicinesRemindersUseCase.execute(userId, medicines);
    }

}
