package com.app.healthyremidersystem.presentation;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.app.healthyremidersystem.Constants;
import com.app.healthyremidersystem.Helper;
import com.app.healthyremidersystem.R;
import com.app.healthyremidersystem.data.MedicineRepository;
import com.app.healthyremidersystem.domain.MedicineRepositoryImpl;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FullScreenAlarmActivity extends AppCompatActivity {

    private static final String TAG = FullScreenAlarmActivity.class.getSimpleName();
    private String medicineId;
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
        medicineId = intent.getStringExtra(Constants.ALERT_ID);
        timePosition = intent.getIntExtra(Constants.POSITION, 0);
        String time = intent.getStringExtra(Constants.TIME);
        timeTextView.setText(time);
        Log.i(TAG, "onCreate: " + medicineId + ", " + timePosition);

    }

    @OnClick(R.id.doneButton)
    public void onDoneClicked() {
        MedicineRepository medicineRepository = new MedicineRepositoryImpl();
        medicineRepository.updateScheduledTimeStatus(new Helper(this).getUserId(), String.valueOf(medicineId), timePosition, true);
        Toast.makeText(this, "Good job!", Toast.LENGTH_SHORT).show();
        finish();
    }

    @OnClick(R.id.ignoreButton)
    public void onIgnoreClicked() {
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