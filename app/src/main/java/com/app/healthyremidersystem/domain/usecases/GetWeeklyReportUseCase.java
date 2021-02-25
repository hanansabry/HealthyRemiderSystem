package com.app.healthyremidersystem.domain.usecases;

import com.app.healthyremidersystem.data.WeeklyReportRepository;
import com.app.healthyremidersystem.model.Medicine;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

public class GetWeeklyReportUseCase {

    private WeeklyReportRepository weeklyReportRepository;

    public GetWeeklyReportUseCase(WeeklyReportRepository weeklyReportRepository) {
        this.weeklyReportRepository = weeklyReportRepository;
    }

    public void execute(MutableLiveData<List<Medicine>> medicines) {
//        weeklyReportRepository.getTakenNumberForMedicine(medicines);
    }
}
