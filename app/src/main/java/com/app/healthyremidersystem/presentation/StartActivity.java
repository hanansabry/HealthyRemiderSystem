package com.app.healthyremidersystem.presentation;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.app.healthyremidersystem.R;

public class StartActivity extends AppCompatActivity {

    @BindView(R.id.btnRegister)
    TextView btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btnRegister)
    public void onRegisterClicked() {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    @OnClick(R.id.btnStart)
    public void onStartClicked() {
        startActivity(new Intent(this, LoginActivity.class));
    }
}