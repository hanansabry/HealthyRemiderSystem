package com.app.healthyremidersystem.presentation;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.app.healthyremidersystem.Constants;
import com.app.healthyremidersystem.Helper;
import com.app.healthyremidersystem.R;
import com.app.healthyremidersystem.data.MedicineRepository;
import com.app.healthyremidersystem.domain.MedicineRepositoryImpl;
import com.app.healthyremidersystem.presentation.notification.AlarmService;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FullScreenAlarmActivity extends AppCompatActivity {

    private static final String TAG = FullScreenAlarmActivity.class.getSimpleName();
    private int medicineId;
    private int timePosition;

    @BindView(R.id.timeTextView)
    TextView timeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_alarm);
        ButterKnife.bind(this);
        turnScreenOnAndKeyguardOff();

        Intent intent = getIntent();
        medicineId = intent.getIntExtra(Constants.ALERT_TYPE_ID, 0);
        timePosition = intent.getIntExtra(Constants.POSITION, 0);
        String time = intent.getStringExtra(Constants.TIME);
        timeTextView.setText(time);
        Log.i(TAG, "onCreate: " + medicineId + ", " + timePosition);

    }

    @OnClick(R.id.doneButton)
    public void onDoneClicked() {
        Intent intentService = new Intent(getApplicationContext(), AlarmService.class);
        getApplicationContext().stopService(intentService);

        MedicineRepository medicineRepository = new MedicineRepositoryImpl();
        medicineRepository.updateScheduledTimeStatus(new Helper(this).getUserId(), String.valueOf(medicineId), timePosition, true);

        Toast.makeText(this, "Good job!", Toast.LENGTH_SHORT).show();
        finish();
    }

    @OnClick(R.id.ignoreButton)
    public void onIgnoreClicked() {
        Intent intentService = new Intent(getApplicationContext(), AlarmService.class);
        getApplicationContext().stopService(intentService);
        finish();
    }

    private void turnScreenOnAndKeyguardOff() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            setTurnScreenOn(true);
        } else {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                    | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                    | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
            );
        }
    }
}