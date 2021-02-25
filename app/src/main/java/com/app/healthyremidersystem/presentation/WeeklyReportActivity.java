package com.app.healthyremidersystem.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.os.Bundle;
import android.widget.Toast;

import com.app.healthyremidersystem.Helper;
import com.app.healthyremidersystem.R;
import com.app.healthyremidersystem.presentation.adapters.MedicinesAdapter;
import com.app.healthyremidersystem.presentation.adapters.WeeklyReportAdapter;
import com.app.healthyremidersystem.presentation.viewmodels.RetrieveMedicinesRemindersViewModel;

public class WeeklyReportActivity extends AppCompatActivity {

    @BindView(R.id.weeklyReportRecyclerView)
    RecyclerView weeklyReportRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_report);

        ButterKnife.bind(this);

        RetrieveMedicinesRemindersViewModel retrieveMedicinesRemindersViewModel
                = new ViewModelProvider(this).get(RetrieveMedicinesRemindersViewModel.class);
        retrieveMedicinesRemindersViewModel.retrieveMedicinesReminders(new Helper(this).getUserId());

        retrieveMedicinesRemindersViewModel.getMedicines().observe(this, medicines -> {
            WeeklyReportAdapter adapter = new WeeklyReportAdapter(medicines);
            weeklyReportRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            weeklyReportRecyclerView.setAdapter(adapter);
        });

        retrieveMedicinesRemindersViewModel.getError().observe(this, error -> {
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        });
    }

    @OnClick(R.id.btnBack)
    public void onBackClicked() {
        onBackPressed();
    }
}