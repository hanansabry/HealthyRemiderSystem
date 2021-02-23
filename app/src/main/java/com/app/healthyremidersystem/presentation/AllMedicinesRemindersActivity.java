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

import com.app.healthyremidersystem.Constants;
import com.app.healthyremidersystem.R;
import com.app.healthyremidersystem.presentation.adapters.MedicinesAdapter;
import com.app.healthyremidersystem.presentation.viewmodels.AddMedicineReminderViewModel;
import com.app.healthyremidersystem.presentation.viewmodels.RetrieveMedicinesRemindersViewModel;

public class AllMedicinesRemindersActivity extends AppCompatActivity implements MedicinesAdapter.MedicinesCallback {

    @BindView(R.id.allRemindersRecyclerView)
    RecyclerView allRemindersRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_medicines_reminders);
        ButterKnife.bind(this);

        RetrieveMedicinesRemindersViewModel retrieveMedicinesRemindersViewModel
                = new ViewModelProvider(this).get(RetrieveMedicinesRemindersViewModel.class);
        retrieveMedicinesRemindersViewModel.retrieveMedicinesReminders(Constants.getUserId(this));

        retrieveMedicinesRemindersViewModel.getMedicines().observe(this, medicines -> {
            MedicinesAdapter adapter = new MedicinesAdapter(medicines, this, false);
            allRemindersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            allRemindersRecyclerView.setAdapter(adapter);
        });

        retrieveMedicinesRemindersViewModel.getError().observe(this, error -> {
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void removeMedicine(String medicineId) {
        AddMedicineReminderViewModel addMedicineReminderViewModel = new ViewModelProvider(this).get(AddMedicineReminderViewModel.class);
        addMedicineReminderViewModel.removeMedicine(Constants.getUserId(this), medicineId);

        addMedicineReminderViewModel.getRemoveSuccess().observe(this, success -> {
            if (success) {
                Toast.makeText(this, "Medicine is removed successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Something wrong is happened, please try again later", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.btnBack)
    public void onBackClicked() {
        onBackPressed();
    }
}