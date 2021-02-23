package com.app.healthyremidersystem.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.widget.Toast;

import com.app.healthyremidersystem.Constants;
import com.app.healthyremidersystem.R;
import com.app.healthyremidersystem.presentation.adapters.MedicinesAdapter;
import com.app.healthyremidersystem.presentation.viewmodels.RetrieveMedicinesRemindersViewModel;

public class WeeklyReportActivity extends AppCompatActivity {

    @BindView(R.id.allRemindersRecyclerView)
    RecyclerView allRemindersRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_report);

        ButterKnife.bind(this);

        RetrieveMedicinesRemindersViewModel retrieveMedicinesRemindersViewModel
                = new ViewModelProvider(this).get(RetrieveMedicinesRemindersViewModel.class);
        retrieveMedicinesRemindersViewModel.retrieveMedicinesReminders(Constants.getUserId(this));

        retrieveMedicinesRemindersViewModel.getMedicines().observe(this, medicines -> {
            MedicinesAdapter adapter = new MedicinesAdapter(medicines, null, true);
            allRemindersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            allRemindersRecyclerView.setAdapter(adapter);
        });

        retrieveMedicinesRemindersViewModel.getError().observe(this, error -> {
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        });
    }
}