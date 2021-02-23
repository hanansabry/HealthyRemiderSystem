package com.app.healthyremidersystem.presentation;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

import com.app.healthyremidersystem.Constants;
import com.app.healthyremidersystem.R;
import com.app.healthyremidersystem.presentation.viewmodels.AddAppointmentViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class HospitalAppointmentActivity extends AppCompatActivity {

    @BindView(R.id.editClinicName)
    TextInputEditText editClinicName;
    @BindView(R.id.editAppointmentDate)
    TextInputEditText editAppointmentDate;
    @BindView(R.id.editAppointmentTime)
    TextInputEditText editAppointmentTime;
    @BindView(R.id.notesEditText)
    TextInputEditText notesEditText;
    private AddAppointmentViewModel addAppointmentViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_appointment);
        ButterKnife.bind(this);

        addAppointmentViewModel = new ViewModelProvider(this).get(AddAppointmentViewModel.class);
        addAppointmentViewModel.getSuccess().observe(this, success -> {
            if (success) {
                Toast.makeText(this, "New appointment is added successfully", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Somthing wrong is happened, please try again later", Toast.LENGTH_SHORT).show();
            }
        });
        addAppointmentViewModel.getDateError().observe(this, error -> {
            editAppointmentDate.setError(error);
        });
        addAppointmentViewModel.getNameError().observe(this, error -> {
            editClinicName.setError(error);
        });
        addAppointmentViewModel.getTimeError().observe(this, error -> {
            editAppointmentTime.setError(error);
        });
    }

    @OnClick(R.id.btnSet)
    public void onSetClicked() {
        String name = Objects.requireNonNull(editClinicName.getText()).toString().trim();
        String date = Objects.requireNonNull(editAppointmentDate.getText()).toString().trim();
        String time = Objects.requireNonNull(editAppointmentTime.getText()).toString().trim();
        String notes = Objects.requireNonNull(notesEditText.getText()).toString().trim();
        addAppointmentViewModel.addNewAppointment(Constants.getUserId(this), name, date, time, notes);
    }

    @OnClick(R.id.setDateImageButton)
    public void onSetDateClicked() {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog mDatePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                editAppointmentDate.setText(String.format(Locale.US, "%d/%d/%d", dayOfMonth, monthOfYear + 1, year));
            }
        }, mYear, mMonth, mDay);
        mDatePicker.show();
        mDatePicker.setTitle("Select Date");
        mDatePicker.show();
    }

    @OnClick(R.id.setTimeImageButton)
    public void onSetTimeClicked() {
        Calendar mCurrentTime = Calendar.getInstance();
        int hour = mCurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mCurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(this, (timePicker, selectedHour, selectedMinute) -> {
            String time = String.format(Locale.US, "%d:%d", selectedHour, selectedMinute);
            editAppointmentTime.setText(time);
        }, hour, minute, false);
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }

    @OnTextChanged(R.id.editAppointmentDate)
    public void removeDateError() {
        editAppointmentDate.setError(null);
    }

    @OnTextChanged(R.id.editAppointmentTime)
    public void removeTimeError() {
        editAppointmentTime.setError(null);
    }

    @OnTextChanged(R.id.editClinicName)
    public void removeNameError() {
        editClinicName.setError(null);
    }
}