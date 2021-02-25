package com.app.healthyremidersystem.presentation;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.DatePicker;
import android.widget.Toast;

import com.app.healthyremidersystem.Constants;
import com.app.healthyremidersystem.Helper;
import com.app.healthyremidersystem.R;
import com.app.healthyremidersystem.model.Appointment;
import com.app.healthyremidersystem.presentation.notification.AlarmController;
import com.app.healthyremidersystem.presentation.viewmodels.AddAppointmentViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
        addAppointmentViewModel.getReturnedAppointment().observe(this, appointment -> {
            if (appointment != null) {
                Toast.makeText(this, "New appointment is added successfully", Toast.LENGTH_SHORT).show();
                //add appointment alert
                setAppointmentAlert(appointment);
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

    private void setAppointmentAlert(Appointment appointment) {
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        Date date = null;
        try {
            //get day, monthe and year of the date
            date = format1.parse(appointment.getDay());
            int day = Integer.parseInt(DateFormat.format("dd", date).toString()); // Thursday
            int monthNumber  = Integer.parseInt(DateFormat.format("MM",   date).toString()); // 06
            int year         = Integer.parseInt(DateFormat.format("yyyy", date).toString()); // 2013
            //get hour and minutes
            int[] hourMinutes = new Helper(this).splitTimeIntoHourAndMinute(appointment.getTime());
            AlarmController alarmController = new AlarmController(this);
            alarmController.setAlarm(day, monthNumber, year, hourMinutes[0], hourMinutes[1], appointment.getPlaceName(), appointment.getId());
        } catch (ParseException e) {
            e.printStackTrace();
        }

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
            String selectedHourS = (selectedHour < 10) ? "0" + selectedHour : String.valueOf(selectedHour);
            String selectedMinuteS = (selectedMinute < 10) ? "0" + selectedMinute : String.valueOf(selectedMinute);
            String time = String.format(Locale.US, "%s:%s", selectedHourS, selectedMinuteS);
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