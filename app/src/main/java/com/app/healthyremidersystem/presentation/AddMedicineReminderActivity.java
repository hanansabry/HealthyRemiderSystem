package com.app.healthyremidersystem.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.app.healthyremidersystem.Constants;
import com.app.healthyremidersystem.Helper;
import com.app.healthyremidersystem.R;
import com.app.healthyremidersystem.model.Medicine;
import com.app.healthyremidersystem.model.ScheduledTime;
import com.app.healthyremidersystem.presentation.adapters.ScheduledTimesAdapter;
import com.app.healthyremidersystem.presentation.notification.AlarmController;
import com.app.healthyremidersystem.presentation.viewmodels.AddMedicineReminderViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class AddMedicineReminderActivity extends AppCompatActivity implements ScheduledTimesAdapter.ScheduledTimeCallback {

    @BindView(R.id.editTextDays)
    TextView daysEditText;
    @BindView(R.id.editTextMedicineName)
    TextInputEditText editTextMedicineName;
    @BindView(R.id.editTextMedicineImage)
    TextInputEditText editTextMedicineImage;
    @BindView(R.id.editTextNumPerDays)
    TextInputEditText editTextNumPerDays;
    @BindView(R.id.scheduledTimesRecyclerView)
    RecyclerView scheduledTimesRecyclerView;
    @BindView(R.id.btnDone)
    Button btnDone;
    private AddMedicineReminderViewModel addMedicineReminderViewModel;
    private StringBuffer selectedDaysString;
    private List<ScheduledTime.Day> selectedDays;
    private String medicineName;
    private String medicineImage;
    private String days;
    private String numPerDays;
    private List<ScheduledTime> allScheduledTimes;
    private List<String> timesPerDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine_reminder);
        ButterKnife.bind(this);

        addMedicineReminderViewModel = new ViewModelProvider(this).get(AddMedicineReminderViewModel.class);
        addMedicineReminderViewModel.getRetrievedMedicine().observe(this, retrievedMedicine -> {
            if (retrievedMedicine != null) {
                Toast.makeText(this, "New medicine reminder is added", Toast.LENGTH_SHORT).show();
                //add alarms for this medicine
                setMedicineAlarms(retrievedMedicine);
                finish();
            } else {
                Toast.makeText(this, "Something wrong is happened, please try again later", Toast.LENGTH_SHORT).show();
            }
        });
        addMedicineReminderViewModel.getError().observe(this, error -> {
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        });
        addMedicineReminderViewModel.getMedicineNameError().observe(this, error -> {
            editTextMedicineName.setError(error);
        });
        addMedicineReminderViewModel.getDaysNumberError().observe(this, error -> {
            daysEditText.setError(error);
        });
        addMedicineReminderViewModel.getNumPerDayError().observe(this, error -> {
            editTextNumPerDays.setError(error);
        });
    }

    private void setMedicineAlarms(Medicine retrievedMedicine) {
        AlarmController alarmController = new AlarmController(this);
        for (int i = 0; i < retrievedMedicine.getScheduledTimes().size(); i++) {
            ScheduledTime scheduledTime = retrievedMedicine.getScheduledTimes().get(i);
            int[] hourMinutes = new Helper(this).splitTimeIntoHourAndMinute(scheduledTime.getTime());
            alarmController.setRepeatingAlarm(i, ScheduledTime.Day.valueOf(scheduledTime.getDay()).getDayValue(),
                    hourMinutes[0],
                    hourMinutes[1],
                    retrievedMedicine.getMedicineName(),
                    retrievedMedicine.getMedicineId(),
                    retrievedMedicine.getMedicineId()+i);
        }
    }

    @OnClick(R.id.btnAllReminders)
    public void onAllRemindersClicked() {
        startActivity(new Intent(this, AllMedicinesRemindersActivity.class));
    }

    @OnClick(R.id.editTextDays)
    public void onDaysClicked() {
        BottomSheetDialog daysDialog = new BottomSheetDialog(this);
        View daysDialogView = getLayoutInflater().inflate(R.layout.choose_days_dialog, null);
        daysDialog.setContentView(daysDialogView);
        daysDialog.show();

        //actions of bottom sheet dialog
        Button okBtn = daysDialogView.findViewById(R.id.okButton);
        Button cancelBtn = daysDialogView.findViewById(R.id.cancelButton);
        okBtn.setOnClickListener(v -> {
            selectedDays = new ArrayList<>();
            if (((CheckBox) daysDialogView.findViewById(R.id.satCheckBox)).isChecked()) {
                selectedDays.add(ScheduledTime.Day.SAT);
            }
            if (((CheckBox) daysDialogView.findViewById(R.id.sunCheckBox)).isChecked()) {
                selectedDays.add(ScheduledTime.Day.SUN);
            }
            if (((CheckBox) daysDialogView.findViewById(R.id.monCheckBox)).isChecked()) {
                selectedDays.add(ScheduledTime.Day.MON);
            }
            if (((CheckBox) daysDialogView.findViewById(R.id.tueCheckBox)).isChecked()) {
                selectedDays.add(ScheduledTime.Day.TUE);
            }
            if (((CheckBox) daysDialogView.findViewById(R.id.wedCheckBox)).isChecked()) {
                selectedDays.add(ScheduledTime.Day.WED);
            }
            if (((CheckBox) daysDialogView.findViewById(R.id.thuCheckbox)).isChecked()) {
                selectedDays.add(ScheduledTime.Day.THU);
            }
            if (((CheckBox) daysDialogView.findViewById(R.id.friCheckBox)).isChecked()) {
                selectedDays.add(ScheduledTime.Day.FRI);
            }

            //get text of days edit text
            selectedDaysString = new StringBuffer();
            for (ScheduledTime.Day day : selectedDays) {
                selectedDaysString. append(day.name()).append(" ");
            }

            daysEditText.setText(selectedDaysString);
            daysDialog.dismiss();
        });

        cancelBtn.setOnClickListener(v -> {
            daysDialog.dismiss();
        });
    }

    private void getMedicineValues () {
        medicineName = Objects.requireNonNull(editTextMedicineName.getText()).toString().trim();
        medicineImage = Objects.requireNonNull(editTextMedicineImage.getText()).toString().trim();
        days = daysEditText.getText().toString().trim();
        numPerDays = Objects.requireNonNull(editTextNumPerDays.getText()).toString().trim();
    }

    @OnClick(R.id.btnSchedule)
    public void onScheduleClicked() {
        getMedicineValues();
        if (addMedicineReminderViewModel.validate(medicineName, days, numPerDays)) {
            scheduledTimesRecyclerView.setVisibility(View.VISIBLE);
            scheduledTimesRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
            ScheduledTimesAdapter adapter = new ScheduledTimesAdapter(Integer.parseInt(numPerDays), this);
            scheduledTimesRecyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onAllTimesSet(List<String> timesPerDays) {
        this.timesPerDay = timesPerDays;
        allScheduledTimes = addMedicineReminderViewModel.getAllScheduledDaysWithTimes(timesPerDays, selectedDays);
        btnDone.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.btnDone)
    public void onDoneClicked() {
        Toast.makeText(this, allScheduledTimes.size()+"", Toast.LENGTH_SHORT).show();
        getMedicineValues();
        addMedicineReminderViewModel.addNewMedicineReminder(Constants.getUserId(this),
                medicineName,
                medicineImage,
                selectedDays.size(),
                timesPerDay,
                allScheduledTimes,
                Integer.parseInt(numPerDays));
    }

    @OnClick(R.id.selectImageButton)
    public void onSelectImageClicked() {
        Toast.makeText(this, "Upload image", Toast.LENGTH_SHORT).show();
    }

    @OnTextChanged(R.id.editTextMedicineName)
    public void removeMedicineNameError() {
        editTextMedicineName.setError(null);
    }

    @OnTextChanged(R.id.editTextDays)
    public void removeDaysError() {
        daysEditText.setError(null);
    }

    @OnTextChanged(R.id.editTextNumPerDays)
    public void removeNumPerDaysError() {
        editTextNumPerDays.setError(null);
    }

    @OnClick(R.id.btnBack)
    public void onBackClicked() {
        onBackPressed();
    }

}