package com.app.healthyremidersystem.presentation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.app.healthyremidersystem.Constants;
import com.app.healthyremidersystem.R;
import com.app.healthyremidersystem.presentation.viewmodels.RegisterViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.editTextUsername)
    TextInputEditText fullNameEditText;
    @BindView(R.id.editTextMobile)
    TextInputEditText phoneEditText;
    @BindView(R.id.editTextEmail)
    TextInputEditText emailEditText;
    @BindView(R.id.editTextPassword)
    TextInputEditText passwordEditText;
    @BindView(R.id.editTextConfirm)
    TextInputEditText confirmEditText;
    @BindView(R.id.editTextWeight)
    TextInputEditText weightEditText;
    @BindView(R.id.editTextLength)
    TextInputEditText lengthEditText;
    @BindView(R.id.genderRadioGroup)
    RadioGroup genderRadioGroup;
    private RegisterViewModel registerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        registerViewModel.getUser().observe(this, user -> {
            if (user != null) {
                registerViewModel.addUser(user);
                //add userid to
            } else {
                Toast.makeText(this, "Can't create account, Please try again later", Toast.LENGTH_SHORT).show();
            }
        });
        registerViewModel.getReturnedUser().observe(this, returnedUser -> {
            if (returnedUser != null) {
                Toast.makeText(this, "You logged in successfully", Toast.LENGTH_SHORT).show();
                //save user id in the sharedpreferences
                saveUserIdLocally(returnedUser.getId());
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            } else {
                Toast.makeText(this, "Can't create account, Please try again later", Toast.LENGTH_SHORT).show();
            }
        });
        registerViewModel.getFullNameError().observe(this, error -> {
            fullNameEditText.setError(error);
        });
        registerViewModel.getPhoneError().observe(this, error -> {
            phoneEditText.setError(error);
        });
        registerViewModel.getEmailError().observe(this, error -> {
            emailEditText.setError(error);
        });
        registerViewModel.getPasswordError().observe(this, error -> {
            passwordEditText.setError(error);
        });
        registerViewModel.getConfirmPasswordError().observe(this, error -> {
            confirmEditText.setError(error);
        });
        registerViewModel.getWeightError().observe(this, error -> {
            weightEditText.setError(error);
        });
        registerViewModel.getLengthError().observe(this, error -> {
            lengthEditText.setError(error);
        });
        registerViewModel.getGenderError().observe(this, error -> {
            Toast.makeText(this, "You must select gender", Toast.LENGTH_SHORT).show();
        });
    }

    private void saveUserIdLocally(String userId) {
        SharedPreferences.Editor sharedPreferences = getSharedPreferences("main", MODE_PRIVATE).edit();
        sharedPreferences.putString(Constants.USER_ID, userId);
        sharedPreferences.apply();
    }

    @OnClick(R.id.btnRegister)
    public void onRegisterClicked() {
        String fullName = Objects.requireNonNull(fullNameEditText.getText()).toString().trim();
        String phone = Objects.requireNonNull(phoneEditText.getText()).toString().trim();
        String email = Objects.requireNonNull(emailEditText.getText()).toString().trim();
        String password = Objects.requireNonNull(passwordEditText.getText()).toString().trim();
        String confirmPassword = Objects.requireNonNull(confirmEditText.getText()).toString().trim();
        double weight = !weightEditText.getText().toString().isEmpty() ? Double.parseDouble(Objects.requireNonNull(weightEditText.getText()).toString().trim()) : 0;
        double length = !lengthEditText.getText().toString().isEmpty() ?Double.parseDouble(Objects.requireNonNull(lengthEditText.getText()).toString().trim()) : 0;
        String gender = genderRadioGroup.getCheckedRadioButtonId() == R.id.maleRadio ? "Male" :
                genderRadioGroup.getCheckedRadioButtonId() == R.id.femaleRadio ? "Female" : null;

        registerViewModel.register(fullName, phone, email, password, confirmPassword, weight, length, gender);
    }

    @OnTextChanged(R.id.editTextUsername)
    public void removeFullNameError() {
        fullNameEditText.setError(null);
    }

    @OnTextChanged(R.id.editTextMobile)
    public void removePhoneError() {
        phoneEditText.setError(null);
    }

    @OnTextChanged(R.id.editTextEmail)
    public void removeEmailError() {
        emailEditText.setError(null);
    }

    @OnTextChanged(R.id.editTextPassword)
    public void removePasswordError() {
        passwordEditText.setError(null);
    }

    @OnTextChanged(R.id.editTextConfirm)
    public void removeConfirmPasswordError() {
        confirmEditText.setError(null);
    }

    @OnTextChanged(R.id.editTextWeight)
    public void removeWeightError() {
        weightEditText.setError(null);
    }

    @OnTextChanged(R.id.editTextLength)
    public void removeLengthError() {
        lengthEditText.setError(null);
    }


    @OnClick(R.id.btnBack)
    public void onBackClicked() {
        onBackPressed();
    }
}