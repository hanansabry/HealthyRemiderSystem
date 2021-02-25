package com.app.healthyremidersystem.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.app.healthyremidersystem.Helper;
import com.app.healthyremidersystem.R;
import com.app.healthyremidersystem.presentation.viewmodels.LoginViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.editTextEmail)
    TextInputEditText emailEditText;
    @BindView(R.id.editTextPassword)
    TextInputEditText passwordEditText;

    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        loginViewModel.getUserId().observe(this, userId -> {
            if (userId != null) {
                Toast.makeText(this, "You logged in successfully", Toast.LENGTH_SHORT).show();
                //save user id in the shared preferences
                new Helper(this).saveUserIdLocally(userId);
                startActivity(new Intent(this, MainActivity.class));
                //TODO: get all old medicines and make alert for them
            } else {
                Toast.makeText(this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
            }
        });
        loginViewModel.getEmailError().observe(this, error -> {
            emailEditText.setError(error);
        });
        loginViewModel.getPasswordError().observe(this, error -> {
            passwordEditText.setError(error);
        });
    }

    @OnClick(R.id.btnLogin)
    public void onLoginClicked() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        loginViewModel.login(email, password);
    }

    @OnTextChanged(R.id.editTextEmail)
    public void removeEmailError() {
        emailEditText.setError(null);
    }

    @OnTextChanged(R.id.editTextPassword)
    public void removePasswordError() {
        passwordEditText.setError(null);
    }

    @OnClick(R.id.btnRegister)
    public void onRegisterClicked() {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    @OnClick(R.id.btnBack)
    public void onBackClicked() {
        onBackPressed();
    }
}