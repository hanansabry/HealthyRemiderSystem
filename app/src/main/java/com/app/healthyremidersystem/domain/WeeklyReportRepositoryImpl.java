package com.app.healthyremidersystem.domain;

import com.app.healthyremidersystem.data.WeeklyReportRepository;
import com.app.healthyremidersystem.model.Medicine;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

public class WeeklyReportRepositoryImpl implements WeeklyReportRepository {
    @Override
    public void getWeeklyReport(MutableLiveData<List<Medicine>> medicines) {

    }
}
