package com.app.healthyremidersystem.presentation.viewmodels;

import com.app.healthyremidersystem.Injection;
import com.app.healthyremidersystem.domain.usecases.LoginUseCase;
import com.google.firebase.auth.FirebaseAuth;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {

    private LoginUseCase loginUseCase;
    private MutableLiveData<Boolean> success = new MutableLiveData<>();
    private MutableLiveData<String> emailError = new MutableLiveData<>();
    private MutableLiveData<String> passwordError = new MutableLiveData<>();

    public LoginViewModel() {
        this.loginUseCase = Injection.getLoginUseCase();
    }

    public MutableLiveData<Boolean> getSuccess() {
        return success;
    }

    public MutableLiveData<String> getEmailError() {
        return emailError;
    }

    public MutableLiveData<String> getPasswordError() {
        return passwordError;
    }

    public void login(String email, String password) {
        if (validate(email, password)) {
            loginUseCase.execute(email, password, success);
        }
    }

    private boolean validate(String email, String password) {
        boolean isValidate = true;
        if (email == null || email.isEmpty()) {
            emailError.setValue("Required");
            isValidate = false;
        }
        if (password == null || password.isEmpty()) {
            passwordError.setValue("Required");
            isValidate = false;
        }
        return isValidate;
    }

    public boolean isLogged() {
        return FirebaseAuth.getInstance().getCurrentUser() != null;
    }

    public void logout() {
        FirebaseAuth.getInstance().signOut();
    }
}
