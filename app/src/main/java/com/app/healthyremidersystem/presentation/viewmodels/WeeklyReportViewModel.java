package com.app.healthyremidersystem.presentation.viewmodels;

import com.app.healthyremidersystem.Injection;
import com.app.healthyremidersystem.domain.usecases.GetWeeklyReportUseCase;
import com.app.healthyremidersystem.model.Medicine;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WeeklyReportViewModel extends ViewModel {

    private GetWeeklyReportUseCase getWeeklyReportUseCase;
    private MutableLiveData<List<Medicine>> medicines = new MutableLiveData<>();
    private MutableLiveData<String> error = new MutableLiveData<>();

    public WeeklyReportViewModel() {
        this.getWeeklyReportUseCase = Injection.getWeeklyReportUseCase();
    }

    public MutableLiveData<List<Medicine>> getMedicines() {
        return medicines;
    }

    public MutableLiveData<String> getError() {
        return error;
    }

    public void getWeeklyReport() {
        getWeeklyReportUseCase.execute(medicines);
    }
}
