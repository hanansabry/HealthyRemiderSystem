package com.app.healthyremidersystem.presentation;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;

import com.app.healthyremidersystem.R;
import com.app.healthyremidersystem.presentation.AllMedicinesRemindersActivity;
import com.app.healthyremidersystem.presentation.DrinkingWaterActivity;
import com.app.healthyremidersystem.presentation.HealthAdviceActivity;
import com.app.healthyremidersystem.presentation.HospitalAppointmentActivity;
import com.app.healthyremidersystem.presentation.AddMedicineReminderActivity;
import com.app.healthyremidersystem.presentation.WeeklyReportActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnBack)
    public void onBackClicked() {
        onBackPressed();
    }

    @OnClick(R.id.btnAllReminders)
    public void onAllRemindersClicked() {
        startActivity(new Intent(this, AllMedicinesRemindersActivity.class));
    }

    @OnClick(R.id.medicineReminderCardView)
    public void onMedicineReminderClicked() {
        startActivity(new Intent(this, AddMedicineReminderActivity.class));
    }

    @OnClick(R.id.weeklyReportCardView)
    public void onWeeklyReportClicked() {
        startActivity(new Intent(this, WeeklyReportActivity.class));
    }

    @OnClick(R.id.healthAdviceCardView)
    public void onHealthAdviceClicked() {
        startActivity(new Intent(this, HealthAdviceActivity.class));
    }

    @OnClick(R.id.hospitalAppointmentCardView)
    public void onHospitalAppointmentClicked() {
        startActivity(new Intent(this, HospitalAppointmentActivity.class));
    }

    @OnClick(R.id.drinkingWaterCardView)
    public void onDrinkingWaterClicked() {
        startActivity(new Intent(this, DrinkingWaterActivity.class));
    }
}