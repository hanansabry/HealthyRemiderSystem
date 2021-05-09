package com.app.healthyremidersystem.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import com.app.healthyremidersystem.Helper;
import com.app.healthyremidersystem.R;
import com.app.healthyremidersystem.presentation.viewmodels.LoginViewModel;

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

    @OnClick(R.id.btnAddHistory)
    public void onAddHistoryClicked() {
        startActivity(new Intent(this, MedicalHistoryActivity.class));
    }

    @OnClick(R.id.btnLogout)
    public void onLogoutClicked() {
        LoginViewModel loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        loginViewModel.logout();
        new Helper(this).removeUserId();
        Intent i = new Intent(this, StartActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
}