package com.app.healthyremidersystem.presentation;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;

import com.app.healthyremidersystem.R;

public class AddMedicineReminderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine_reminder);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnAllReminders)
    public void onAllRemindersClicked() {
        startActivity(new Intent(this, AllMedicinesRemindersActivity.class));
    }

    @OnClick(R.id.btnBack)
    public void onBackClicked() {
        onBackPressed();
    }
}