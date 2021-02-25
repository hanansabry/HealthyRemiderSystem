package com.app.healthyremidersystem.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.app.healthyremidersystem.R;
import com.app.healthyremidersystem.presentation.notification.AlarmController;
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

//        AlarmController alarmController = new AlarmController(this);
//        alarmController.setAlarm(0, 5, 3, 10, "temp", 100681144);
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