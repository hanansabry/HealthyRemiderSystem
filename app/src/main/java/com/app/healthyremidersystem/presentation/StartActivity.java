package com.app.healthyremidersystem.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.app.healthyremidersystem.R;
import com.app.healthyremidersystem.presentation.notification.Alarm;
import com.app.healthyremidersystem.presentation.viewmodels.LoginViewModel;

public class StartActivity extends AppCompatActivity {

    @BindView(R.id.btnRegister)
    TextView btnRegister;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);

//        Alarm alarm = new Alarm(0, 2021, 1, 26,
//                6, 45,
//                "temp",
//                100607722,
//                100607722,
//                false);
//        alarm.schedule(this);
//        Log.i("ALARM_STEPS", "setting alarm in start activity");
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
    }

    @OnClick(R.id.btnRegister)
    public void onRegisterClicked() {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    @OnClick(R.id.btnStart)
    public void onStartClicked() {
        if (loginViewModel.isLogged()) {
            startActivity(new Intent(this, MainActivity.class));
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}